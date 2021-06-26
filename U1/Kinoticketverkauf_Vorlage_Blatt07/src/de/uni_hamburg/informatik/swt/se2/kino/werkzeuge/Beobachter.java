package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge;

/**
 * 
 * @author Taha
 * 
 *  Die Klasse Beobachter ist ein Interface für alle andere Klassen, die benachrichtigt
 *  werden müssen, wenn es eine Änderung gibt.
 */

public interface Beobachter
{
    void beachteAenderung(Object o);

}
