package juggernaut.action;
import juggernaut.entity.*;

public abstract class Action {
	Actor target;
	public abstract void execute();
}
