package cyee.view;

import android.content.ComponentName;
import android.content.Intent;
import android.view.KeyEvent;

public interface CyeeMenu {

	/**
	 * This is the part of an order integer that the user can provide.
	 * 
	 * @hide
	 */
    int USER_MASK = 0x0000ffff;
	/**
	 * Bit shift of the user portion of the order integer.
	 * 
	 * @hide
	 */
    int USER_SHIFT = 0;

	/**
	 * This is the part of an order integer that supplies the category of the item.
	 * 
	 * @hide
	 */
    int CATEGORY_MASK = 0xffff0000;
	/**
	 * Bit shift of the category portion of the order integer.
	 * 
	 * @hide
	 */
    int CATEGORY_SHIFT = 16;

	/**
	 * Value to use for group and item identifier integers when you don't care about them.
	 */
    int NONE = 0;

	/**
	 * First value for group and item identifier integers.
	 */
    int FIRST = 1;

	// Implementation note: Keep these CATEGORY_* in sync with the category enum
	// in attrs.xml

	/**
	 * Category code for the order integer for items/groups that are part of a container -- or/add this with
	 * your base value.
	 */
    int CATEGORY_CONTAINER = 0x00010000;

	/**
	 * Category code for the order integer for items/groups that are provided by the system -- or/add this
	 * with your base value.
	 */
    int CATEGORY_SYSTEM = 0x00020000;

	/**
	 * Category code for the order integer for items/groups that are user-supplied secondary (infrequently
	 * used) options -- or/add this with your base value.
	 */
    int CATEGORY_SECONDARY = 0x00030000;

	/**
	 * Category code for the order integer for items/groups that are alternative actions on the data that is
	 * currently displayed -- or/add this with your base value.
	 */
    int CATEGORY_ALTERNATIVE = 0x00040000;

	/**
	 * Flag for {@link #addIntentOptions}: if set, do not automatically remove any existing menu items in the
	 * same group.
	 */
    int FLAG_APPEND_TO_GROUP = 0x0001;

	/**
	 * Flag for {@link #performShortcut}: if set, do not close the menu after executing the shortcut.
	 */
    int FLAG_PERFORM_NO_CLOSE = 0x0001;

	/**
	 * Flag for {@link #performShortcut(int, KeyEvent, int)}: if set, always close the menu after executing
	 * the shortcut. Closing the menu also resets the prepared state.
	 */
    int FLAG_ALWAYS_PERFORM_CLOSE = 0x0002;

	/**
	 * Add a new item to the menu. This item displays the given title for its label.
	 * 
	 * @param title
	 *            The text to display for the item.
	 * @return The newly added menu item.
	 */
    CyeeMenuItem add(CharSequence title);

	/**
	 * Add a new item to the menu. This item displays the given title for its label.
	 * 
	 * @param titleRes
	 *            Resource identifier of title string.
	 * @return The newly added menu item.
	 */
    CyeeMenuItem add(int titleRes);

	/**
	 * Add a new item to the menu. This item displays the given title for its label.
	 * 
	 * @param groupId
	 *            The group identifier that this item should be part of. This can be used to define groups of
	 *            items for batch state changes. Normally use {@link #NONE} if an item should not be in a
	 *            group.
	 * @param itemId
	 *            Unique item ID. Use {@link #NONE} if you do not need a unique ID.
	 * @param order
	 *            The order for the item. Use {@link #NONE} if you do not care about the order. See
	 *            {@link CyeeMenuItem#getOrder()}.
	 * @param title
	 *            The text to display for the item.
	 * @return The newly added menu item.
	 */
    CyeeMenuItem add(int groupId, int itemId, int order, CharSequence title);

	/**
	 * Variation on {@link #add(int, int, int, CharSequence)} that takes a string resource identifier instead
	 * of the string itself.
	 * 
	 * @param groupId
	 *            The group identifier that this item should be part of. This can also be used to define
	 *            groups of items for batch state changes. Normally use {@link #NONE} if an item should not be
	 *            in a group.
	 * @param itemId
	 *            Unique item ID. Use {@link #NONE} if you do not need a unique ID.
	 * @param order
	 *            The order for the item. Use {@link #NONE} if you do not care about the order. See
	 *            {@link CyeeMenuItem#getOrder()}.
	 * @param titleRes
	 *            Resource identifier of title string.
	 * @return The newly added menu item.
	 */
    CyeeMenuItem add(int groupId, int itemId, int order, int titleRes);

	/**
	 * Add a new sub-menu to the menu. This item displays the given title for its label. To modify other
	 * attributes on the CyeeSubMenu's menu item, use {@link CyeeSubMenu#getItem()}.
	 * 
	 * @param title
	 *            The text to display for the item.
	 * @return The newly added sub-menu
	 */
	CyeeSubMenu addSubMenu(final CharSequence title);

	/**
	 * Add a new sub-menu to the menu. This item displays the given title for its label. To modify other
	 * attributes on the CyeeSubMenu's menu item, use {@link CyeeSubMenu#getItem()}.
	 * 
	 * @param titleRes
	 *            Resource identifier of title string.
	 * @return The newly added sub-menu
	 */
	CyeeSubMenu addSubMenu(final int titleRes);

	/**
	 * Add a new sub-menu to the menu. This item displays the given <var>title</var> for its label. To modify
	 * other attributes on the CyeeSubMenu's menu item, use {@link CyeeSubMenu#getItem()}.
	 * <p>
	 * Note that you can only have one level of sub-menus, i.e. you cannnot add a CyeeSubMenu to a CyeeSubMenu: An
	 * {@link UnsupportedOperationException} will be thrown if you try.
	 * 
	 * @param groupId
	 *            The group identifier that this item should be part of. This can also be used to define
	 *            groups of items for batch state changes. Normally use {@link #NONE} if an item should not be
	 *            in a group.
	 * @param itemId
	 *            Unique item ID. Use {@link #NONE} if you do not need a unique ID.
	 * @param order
	 *            The order for the item. Use {@link #NONE} if you do not care about the order. See
	 *            {@link CyeeMenuItem#getOrder()}.
	 * @param title
	 *            The text to display for the item.
	 * @return The newly added sub-menu
	 */
	CyeeSubMenu addSubMenu(final int groupId, final int itemId, int order, final CharSequence title);

	/**
	 * Variation on {@link #addSubMenu(int, int, int, CharSequence)} that takes a string resource identifier
	 * for the title instead of the string itself.
	 * 
	 * @param groupId
	 *            The group identifier that this item should be part of. This can also be used to define
	 *            groups of items for batch state changes. Normally use {@link #NONE} if an item should not be
	 *            in a group.
	 * @param itemId
	 *            Unique item ID. Use {@link #NONE} if you do not need a unique ID.
	 * @param order
	 *            The order for the item. Use {@link #NONE} if you do not care about the order. See
	 *            {@link CyeeMenuItem#getOrder()}.
	 * @param titleRes
	 *            Resource identifier of title string.
	 * @return The newly added sub-menu
	 */
	CyeeSubMenu addSubMenu(int groupId, int itemId, int order, int titleRes);

	/**
	 * Add a group of menu items corresponding to actions that can be performed for a particular Intent. The
	 * Intent is most often configured with a null action, the data that the current activity is working with,
	 * and includes either the {@link Intent#CATEGORY_ALTERNATIVE} or
	 * {@link Intent#CATEGORY_SELECTED_ALTERNATIVE} to find activities that have said they would like to be
	 * included as optional action. You can, however, use any Intent you want.
	 * 
	 * <p>
	 * See {@link android.content.pm.PackageManager#queryIntentActivityOptions} for more * details on the
	 * <var>caller</var>, <var>specifics</var>, and <var>intent</var> arguments. The list returned by that
	 * function is used to populate the resulting menu items.
	 * 
	 * <p>
	 * All of the menu items of possible options for the intent will be added with the given group and id. You
	 * can use the group to control ordering of the items in relation to other items in the menu. Normally
	 * this function will automatically remove any existing items in the menu in the same group and place a
	 * divider above and below the added items; this behavior can be modified with the <var>flags</var>
	 * parameter. For each of the generated items {@link CyeeMenuItem#setIntent} is called to associate the
	 * appropriate Intent with the item; this means the activity will automatically be started for you without
	 * having to do anything else.
	 * 
	 * @param groupId
	 *            The group identifier that the items should be part of. This can also be used to define
	 *            groups of items for batch state changes. Normally use {@link #NONE} if the items should not
	 *            be in a group.
	 * @param itemId
	 *            Unique item ID. Use {@link #NONE} if you do not need a unique ID.
	 * @param order
	 *            The order for the items. Use {@link #NONE} if you do not care about the order. See
	 *            {@link CyeeMenuItem#getOrder()}.
	 * @param caller
	 *            The current activity component name as defined by queryIntentActivityOptions().
	 * @param specifics
	 *            Specific items to place first as defined by queryIntentActivityOptions().
	 * @param intent
	 *            Intent describing the kinds of items to populate in the list as defined by
	 *            queryIntentActivityOptions().
	 * @param flags
	 *            Additional options controlling how the items are added.
	 * @param outSpecificItems
	 *            Optional array in which to place the menu items that were generated for each of the
	 *            <var>specifics</var> that were requested. Entries may be null if no activity was found for
	 *            that specific action.
	 * @return The number of menu items that were added.
	 * 
	 * @see #FLAG_APPEND_TO_GROUP
	 * @see CyeeMenuItem#setIntent
	 * @see android.content.pm.PackageManager#queryIntentActivityOptions
	 */
    int addIntentOptions(int groupId, int itemId, int order, ComponentName caller, Intent[] specifics,
                         Intent intent, int flags, CyeeMenuItem[] outSpecificItems);

	/**
	 * Remove the item with the given identifier.
	 * 
	 * @param id
	 *            The item to be removed. If there is no item with this identifier, nothing happens.
	 */
    void removeItem(int id);

	/**
	 * Remove all items in the given group.
	 * 
	 * @param groupId
	 *            The group to be removed. If there are no items in this group, nothing happens.
	 */
    void removeGroup(int groupId);

	/**
	 * Remove all existing items from the menu, leaving it empty as if it had just been created.
	 */
    void clear();

	/**
	 * Control whether a particular group of items can show a check mark. This is similar to calling
	 * {@link CyeeMenuItem#setCheckable} on all of the menu items with the given group identifier, but in
	 * addition you can control whether this group contains a mutually-exclusive set items. This should be
	 * called after the items of the group have been added to the menu.
	 * 
	 * @param group
	 *            The group of items to operate on.
	 * @param checkable
	 *            Set to true to allow a check mark, false to disallow. The default is false.
	 * @param exclusive
	 *            If set to true, only one item in this group can be checked at a time; checking an item will
	 *            automatically uncheck all others in the group. If set to false, each item can be checked
	 *            independently of the others.
	 * 
	 * @see CyeeMenuItem#setCheckable
	 * @see CyeeMenuItem#setChecked
	 */
    void setGroupCheckable(int group, boolean checkable, boolean exclusive);

	/**
	 * Show or hide all menu items that are in the given group.
	 * 
	 * @param group
	 *            The group of items to operate on.
	 * @param visible
	 *            If true the items are visible, else they are hidden.
	 * 
	 * @see CyeeMenuItem#setVisible
	 */
    void setGroupVisible(int group, boolean visible);

	/**
	 * Enable or disable all menu items that are in the given group.
	 * 
	 * @param group
	 *            The group of items to operate on.
	 * @param enabled
	 *            If true the items will be enabled, else they will be disabled.
	 * 
	 * @see CyeeMenuItem#setEnabled
	 */
    void setGroupEnabled(int group, boolean enabled);

	/**
	 * Return whether the menu currently has item items that are visible.
	 * 
	 * @return True if there is one or more item visible, else false.
	 */
    boolean hasVisibleItems();

	/**
	 * Return the menu item with a particular identifier.
	 * 
	 * @param id
	 *            The identifier to find.
	 * 
	 * @return The menu item object, or null if there is no item with this identifier.
	 */
    CyeeMenuItem findItem(int id);

	/**
	 * Get the number of items in the menu. Note that this will change any times items are added or removed
	 * from the menu.
	 * 
	 * @return The item count.
	 */
    int size();

	/**
	 * Gets the menu item at the given index.
	 * 
	 * @param index
	 *            The index of the menu item to return.
	 * @return The menu item.
	 * @exception IndexOutOfBoundsException
	 *                when {@code index < 0 || >= size()}
	 */
    CyeeMenuItem getItem(int index);

	/**
	 * Closes the menu, if open.
	 */
    void close();

	/**
	 * Execute the menu item action associated with the given shortcut character.
	 * 
	 * @param keyCode
	 *            The keycode of the shortcut key.
	 * @param event
	 *            Key event message.
	 * @param flags
	 *            Additional option flags or 0.
	 * 
	 * @return If the given shortcut exists and is shown, returns true; else returns false.
	 * 
	 * @see #FLAG_PERFORM_NO_CLOSE
	 */
    boolean performShortcut(int keyCode, KeyEvent event, int flags);

	/**
	 * Is a keypress one of the defined shortcut keys for this window.
	 * 
	 * @param keyCode
	 *            the key code from {@link KeyEvent} to check.
	 * @param event
	 *            the {@link KeyEvent} to use to help check.
	 */
	boolean isShortcutKey(int keyCode, KeyEvent event);

	/**
	 * Execute the menu item action associated with the given menu identifier.
	 * 
	 * @param id
	 *            Identifier associated with the menu item.
	 * @param flags
	 *            Additional option flags or 0.
	 * 
	 * @return If the given identifier exists and is shown, returns true; else returns false.
	 * 
	 * @see #FLAG_PERFORM_NO_CLOSE
	 */
    boolean performIdentifierAction(int id, int flags);

	/**
	 * Control whether the menu should be running in qwerty mode (alphabetic shortcuts) or 12-key mode
	 * (numeric shortcuts).
	 * 
	 * @param isQwerty
	 *            If true the menu will use alphabetic shortcuts; else it will use numeric shortcuts.
	 */
    void setQwertyMode(boolean isQwerty);

}
