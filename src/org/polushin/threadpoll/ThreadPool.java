package org.polushin.threadpoll;

/**
 * Пул потоков.
 */
public class ThreadPool {

	private final PooledThread[] pool;
	private final ThreadGroup group;

	public ThreadPool(String name, int poolSize) {
		group = new ThreadGroup(ThreadPool.class.getSimpleName() + ": " + name);
		pool = new PooledThread[poolSize];
		for (int i = 0; i < poolSize; i++)
			pool[i] = new PooledThread(group, i);
	}

	/**
	 * @return Размер пула потоков.
	 */
	public int getPoolSize() {
		return pool.length;
	}

	/**
	 * Запускает задачу на исполнение.
	 * Если нет свободных потоков, ожидает освобождения.
	 *
	 * @param task Задача на исполнение.
	 *
	 * @throws InterruptedException Прерывание ожидания освобождения потока.
	 */
	public void runTask(Runnable task) throws InterruptedException {
		if (task == null)
			throw new IllegalArgumentException("Task cannot be null!");

		PooledThread thread = null;

		synchronized (group) {
			while (thread == null) {
				for (PooledThread candidate : pool)
					if (candidate.isFree()) {
						thread = candidate;
						break;
					}

				if (thread == null)
					group.wait();
			}
			thread.setTask(task);
		}
	}

	/**
	 * Посылает сигнал на прерывание всему пулу потоков и дожидается их завершения.
	 */
	public void stopAll() {
		group.interrupt();
		for (PooledThread thread : pool) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
