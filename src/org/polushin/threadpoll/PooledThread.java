package org.polushin.threadpoll;

/**
 * Поток из пула потоков.
 */
public class PooledThread extends Thread {

	private final ThreadGroup group;
	private final Object mutex = new Object();

	private volatile boolean free = true;
	private volatile boolean interrupted;
	private Runnable currentTask;

	public PooledThread(ThreadGroup group, int id) {
		super(group, PooledThread.class.getSimpleName() + "_" + id);
		this.group = group;
	}

	/**
	 * @return Свободен ли поток от задач.
	 */
	public boolean isFree() {
		return free;
	}

	/**
	 * Устанавливает новую задачу по завершению предыдущей.
	 * Если предыдущая задача выполняется, блокирует поток.
	 *
	 * @param task Новая задача.
	 */
	public void setTask(Runnable task) {
		synchronized (mutex) {
			currentTask = task;
			free = false;
			mutex.notify();
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				synchronized (mutex) {
					while (free)
						mutex.wait();

					currentTask.run();
					free = true;

					if (interrupted)
						return;

					synchronized (group) {
						group.notify();
					}
				}
			}
		} catch (InterruptedException ignored) {}
	}

	@Override
	public void interrupt() {
		interrupted = true;
		super.interrupt();
	}
}
