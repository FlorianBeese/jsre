package rocks.jsre.action;

/**
 * Implement this interface, to create actions, that can retrieve parameters
 * from the configuration.
 * 
 * Take a look at {@link rocks.jsre.action.Action the Action inferace} for usage
 * information.
 * 
 * @author Florian Beese
 *
 * @param <T> The type of the parameter, e.g. String.
 */
public interface ParameterizedAction<T> extends Action {

	public void execute(T param);
}
