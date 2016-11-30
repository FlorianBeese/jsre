package rocks.jsre.action;

/**
 * The Action base interface.
 * All actions derive from this interface.
 * 
 * You can use actions as follows:
 * <ul>
 * <li>First register the action at your rule engine object (see:
 * {@link rocks.jsre.RuleEngine#registerAction(String, Action)
 * registerAction(String, Action)}).</li>
 * <li>Next: create a rule in your configuration and add the action under
 * "executionActions".</li>
 * </ul>
 * 
 * See docs for further details.
 * 
 * @author Florian Beese
 *
 */
public interface Action {

}
