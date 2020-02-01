/** EventButtonManager (EBM) is a manager for buttons and eventhandlers that allows you to store, manage, link and unlink eventhandlers and buttons
 *
 * @author Logan Hart
 * @version V1.2-1.16.20
 */
package hart.Valkyrie.objects;

import hart.Valkyrie.exceptions.DuplicateNameException;
import hart.Valkyrie.exceptions.NonExistantDataException;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class EventButtonManager
{
	private final static double EVENTBUTTONMANAGER_VERSION = 1.2;

	private NamedArrayList<Button> buttons;
	private NamedArrayList<EventHandler> events;

	public EventButtonManager()
	{
		super();
		buttons = new NamedArrayList<Button>(false);
		events = new NamedArrayList<EventHandler>(false);
	}

	/** @return Returns the NAL for Button Objects */
	public NamedArrayList<Button> exportButtons()
	{
		return buttons;
	}

	/** @param ibuttons Import a NAL from Buttons from another EBM */
	public void importButtons(NamedArrayList<Button> ibuttons)
	{
		buttons = ibuttons;
	}

	/** @return Returns the NAL for Event Handlers */
	public NamedArrayList<EventHandler> exportEvents()
	{
		return events;
	}

	/** @param iEvents Import a Event Handler NAL from another EBM */
	public void importEvents(NamedArrayList<EventHandler> iEvents)
	{
		events = iEvents;
	}

	/**
	 * @return Returns the button registered under that name
	 * @throws NonExistantDataException
	 */
	public Button getButton(String si) throws NonExistantDataException
	{
		return buttons.get(si);
	}

	/**
	 * @param fname   Name of button to be replaced
	 * @param ibutton Button to replace with
	 * @throws NonExistantDataException
	 */
	public void replaceButton(String fname, Button ibutton) throws NonExistantDataException
	{
		buttons.set(fname, ibutton);
	}

	/**
	 * @param fname   Name to register the button under
	 * @param ibutton button to register
	 * @throws DuplicateNameException
	 */
	public void makeButton(String fname, Button ibutton) throws DuplicateNameException
	{
		buttons.add(fname, ibutton);
	}

	/**
	 * @param fname   Name to register the button under
	 * @param ibutton button to register
	 * @param eventh  Event to link to button
	 * @throws DuplicateNameException
	 */
	public void makeButton(String fname, Button ibutton, EventHandler eventh) throws DuplicateNameException
	{
		ibutton.setOnAction(eventh);
		buttons.add(fname, ibutton);
	}

	/**
	 * @param fname   Name to register the button under
	 * @param ibutton button to register
	 * @param eventst String to link button to
	 * @throws NonExistantDataException
	 * @throws DuplicateNameException
	 */
	public void makeButton(String fname, Button ibutton, String eventst)
			throws NonExistantDataException, DuplicateNameException
	{
		ibutton.setOnAction(events.get(eventst));
		buttons.add(fname, ibutton);
	}

	/**
	 * @param si String to search EventHandler NAL form
	 * @throws NonExistantDataException
	 */
	public EventHandler getEvent(String si) throws NonExistantDataException
	{
		return events.get(si);
	}

	/**
	 * @param fname  Name of Event to be replaced
	 * @param iEvent new event
	 * @throws NonExistantDataException
	 */
	public void replaceEvent(String fname, EventHandler iEvent) throws NonExistantDataException
	{
		events.set(fname, iEvent);
	}

	/**
	 * @param fname  Name of new Event
	 * @param iEvent Event Object to be saved
	 * @throws DuplicateNameException
	 */
	public void makeEvent(String fname, EventHandler iEvent) throws DuplicateNameException
	{
		events.add(fname, iEvent);
	}

	/**
	 * Link two pre-registered buttons and event
	 * 
	 * @param buttonName Name of button to be linked
	 * @param eventName  Name of event to be linked
	 * @throws NonExistantDataException
	 */
	public void makeLink(String buttonName, String eventName) throws NonExistantDataException
	{
		(buttons.get(buttonName)).setOnAction(events.get(eventName));
	}

	/**
	 * @param buttonName String name of button to be unlinked
	 * @throws NonExistantDataException
	 */
	public void removeLink(String buttonName) throws NonExistantDataException
	{
		(buttons.get(buttonName)).setOnAction(null);
	}

	public static double getEventbuttonmanagerVersion()
	{
		return EVENTBUTTONMANAGER_VERSION;
	}

}
