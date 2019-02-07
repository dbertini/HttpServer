package it.db.httpserver.executors;

import java.util.concurrent.Executor;

public class TestExecutor implements Executor {

	@Override
	public void execute(Runnable command) {
		String lsCommand = command.getClass().getName();
		System.out.println(lsCommand);
	}

}
