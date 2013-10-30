package br.android.goeurotest.tasks;

import br.android.goeurotest.api.OperationResult;

/**
 * Notifiable class.
 * 
 * @author Rodrigo Cericatto
 * @since Set 9, 2012
 */
public interface Notifiable {
	
	/**
	 * Called when a task is finished.
	 * 
	 * @param type The type of the task.
	 * @param result The OperationResult object.
	 */
	public void taskFinished(int type, OperationResult result);
}