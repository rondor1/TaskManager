// NotificationAIDL.aidl
package ra17_2014.pnrs1.rtrk.taskmanager.taskmanager;

// Declare any non-default types here with import statements

interface NotificationAIDL {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     void taskAdded();
     void taskEdited();
     void taskDeleted();

}
