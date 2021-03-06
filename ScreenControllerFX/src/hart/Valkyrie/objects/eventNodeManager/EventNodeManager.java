/** EventButtonManager (EBM) is a manager for nodes and eventhandlers that allows you to store, manage, link and unlink eventhandlers and nodes
 *
 * @author Logan Hart
 * @version V3.0
 */
package hart.Valkyrie.objects.eventNodeManager;

//TODO update javadoc
import java.util.HashMap;
import java.util.Map;
import hart.Valkyrie.SuperConductor;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;

public class EventNodeManager<T extends Node, E extends Event, Y extends MethodParser<T, E>> implements SuperConductor
{

	private Map<String, T> nodes;
	private Map<String, EventHandler<E>> events;
	private Y operator;

	public EventNodeManager(Y y)
	{
		super();
		nodes = new HashMap<>();
		events = new HashMap<>();
		operator = y;
	}

	/** @return Returns the NAL for T Objects */
	public Map<String, T> exportnodes()
	{
		return nodes;
	}

	/** @param inodes Import a NAL from nodes from another EBM */
	public void importNodes(Map<String, T> inodes)
	{
		nodes = inodes;
	}

	/** @return Returns the NAL for Event Handlers */
	public Map<String, EventHandler<E>> exportEvents()
	{
		return events;
	}

	/** @param iEvents Import a Event Handler NAL from another EBM */
	public void importEvents(Map<String, EventHandler<E>> iEvents)
	{
		events = iEvents;
	}

	/**
	 * @return Returns the T registered under that name
	 */
	public T getNode(String si)
	{
		return nodes.get(si);
	}

	/**
	 * @param fname   Name to register the T under
	 * @param ibutton T to register
	 * @throws DuplicateNameException
	 * @return T you added
	 */
	public T setNode(String fname, T ibutton)
	{
		nodes.put(fname, ibutton);
		return nodes.get(fname);
	}

	/**
	 * @param fname   Name to register the T under
	 * @param ibutton T to register
	 * @param eventh  Event to link to T
	 * @return
	 */
	public T setNode(String fname, T ibutton, EventHandler<E> eventh)
	{
		// ibutton.setOnAction(eventh);
		nodes.put(fname, operator.link(ibutton, eventh));
		return nodes.get(fname);
	}

	/**
	 * @param fname   Name to register the T under
	 * @param ibutton T to register
	 * @param eventst String to link T to
	 * @return
	 */
	public T setNode(String fname, T ibutton, String eventst)
	{
		// ibutton.setOnAction(events.get(eventst));
		nodes.put(fname, operator.link(ibutton, events.get(eventst)));
		return nodes.get(fname);
	}

	/**
	 * @param si String to search EventHandler NAL form
	 */
	public EventHandler<E> getEvent(String si)
	{
		return events.get(si);
	}

	/**
	 * @param fname  Name of new Event
	 * @param iEvent Event Object to be saved
	 */
	public EventHandler<E> setEvent(String fname, EventHandler<E> iEvent)
	{
		events.put(fname, iEvent);
		return events.get(fname);
	}

	/**
	 * Link two pre-registered nodes and event
	 * 
	 * @param buttonName Name of T to be linked
	 * @param eventName  Name of event to be linked
	 */
	public void setLink(String buttonName, String eventName)
	{
		nodes.put(buttonName, operator.link(nodes.get(buttonName), events.get(eventName)));
		// nodes.get(buttonName).setOnAction(events.get(eventName));
	}

	/**
	 * @param buttonName String name of T to be unlinked
	 */
	public void removeLink(String buttonName)
	{
		nodes.put(buttonName, operator.link(nodes.get(buttonName), null));
		// nodes.get(buttonName).setOnAction(null);
	}

	@Override
	public void deConstruct()
	{
		nodes = null;
		events = null;
		operator.deConstruct();
		operator = null;
	}

}
