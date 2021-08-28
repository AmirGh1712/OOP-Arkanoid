/**
 * The interface for task.
 *
 * @author Amir Gheriani
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