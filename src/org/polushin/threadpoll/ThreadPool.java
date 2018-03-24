package org.polushin.threadpoll;

/**
 * Пул потоков.
 */
public class ThreadPool {

	private final PooledThread[] pool;
	private final ThreadGroup group = new ThreadGroup(ThreadPool.class.getSimpleName());

	public ThreadPool(int poolSize) {
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

		synchronized (pool) {
			while (thread == null) {
				for (PooledThread candidate : pool)
					if (candidate.isFree()) {
						thread = candidate;
						break;
					}
				group.wait();
			}
			thread.setTask(task);
		}

	}

}
