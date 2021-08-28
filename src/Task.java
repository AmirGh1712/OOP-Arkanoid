/**
 * The interface for task.
 *
 * @author Amir Gheriani 212938724
 *
 * @param <T> the return value.
 */
public interface Task<T> {
    /**Run the task.
     *
     * @return the return value.
     */
    T run();
}