package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.platzverkauf;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JLabel;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Platz;

/**
 * Widget, das die PlÃ¤tze in einem Kinosaal grafisch darstellt und es
 * ermÃ¶glicht, PlÃ¤tze fÃ¼r den Verkauf auszuwÃ¤hlen.
 * 
 * HierfÃ¼r wurde ein eigenes Widget entwickelt, damit das Werkzeug nur einen
 * Listener fÃ¼r ein spezielles Auswahl-Event registrieren muss, statt an jedem
 * einzelnen Button einen ActionListener registrieren zu mÃ¼ssen. Dieses Widget
 * kapselt also die Abbildung der Events der einzelnen Buttons auf ein
 * Auswahl-Event.
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
class JPlatzplan extends JComponent
{
    public static Dimension PLATZBUTTON_GROESSE = new Dimension(22, 22);

    private JPlatzButton[][] _buttons;
    private ActionListener _buttonListener;
    private Set<Platz> _ausgewaehltePlaetze;
    private List<PlatzSelectionListener> _selectionListener;

    /**
     * Erzeugt einen neuen, leeren Platzplan.
     */
    public JPlatzplan()
    {
        setLayout(new GridBagLayout());
        erzeugePlatzAuswahlListener();
        _ausgewaehltePlaetze = new HashSet<Platz>();
        _selectionListener = new ArrayList<PlatzSelectionListener>();
    }

    /**
     * Erzeugt und registriert den Listener, der darauf reagiert, wenn durch
     * DrÃ¼cken eines der Buttons ein Platz ausgewÃ¤hlt wird.
     */
    private void erzeugePlatzAuswahlListener()
    {
        _buttonListener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // Es gibt nur einen Listener fÃ¼r alle Buttons. Welcher der
                // Buttons gedrÃ¼ckt wurde, muss deshalb aus dem Event ausgelesen
                // werden.
                JPlatzButton button = (JPlatzButton) e.getSource();

                // Je nachdem, ob der Platz bereits ausgewÃ¤hlt war, wird er aus
                // der Menge der ausgewÃ¤hlten PlÃ¤tze entfernt oder dieser
                // hinzugefÃ¼gt.
                Platz platz = button.getPlatz();
                if (_ausgewaehltePlaetze.contains(platz))
                {
                    _ausgewaehltePlaetze.remove(platz);
                    button.setAusgewaehlt(false);
                    informiereSelectionListener(_ausgewaehltePlaetze);
                }
                else
                {
                    _ausgewaehltePlaetze.add(platz);
                    button.setAusgewaehlt(true);
                    informiereSelectionListener(_ausgewaehltePlaetze);
                }
            }
        };
    }

    /**
     * FÃ¼gt einen Listener hinzu, der bei Ã„nderungen der Auswahl benachrichtigt
     * wird.
     * 
     * @param listener der Listener.
     */
    public void addPlatzSelectionListener(PlatzSelectionListener listener)
    {
        _selectionListener.add(listener);
    }

    /**
     * Entfernt einen Listener.
     * 
     * @param listener der Listener.
     */
    public void removePlatzSelectionListener(PlatzSelectionListener listener)
    {
        _selectionListener.remove(listener);
    }

    /**
     * Benachrichtigt die SelectionListener, dass sich die Auswahl geÃ¤ndert hat.
     * 
     * @param ausgewaehltePlaetze die neue Auswahl.
     */
    private void informiereSelectionListener(Set<Platz> ausgewaehltePlaetze)
    {
        PlatzSelectionEvent event = new PlatzSelectionEvent(this,
                ausgewaehltePlaetze);
        for (PlatzSelectionListener listener : _selectionListener)
        {
            listener.auswahlGeaendert(event);
        }
    }

    /**
     * Setzt die Anzahl der PlÃ¤tze, die in diesem Platzplan zur Auswahl
     * angeboten werden. Achtung, nach dem Aufruf dieser Methode werden zunÃ¤chst
     * alle PlÃ¤tze als frei angezeigt!
     * 
     * @param anzahlReihen die Anzahl der Reihen
     * @param anzahlSitzeProReihe die Anzahl der PlÃ¤tze pro Reihe
     * 
     * @require anzahlReihen >= 0
     * @require anzahlSitzeProReihe >= 0
     */
    public void setAnzahlPlaetze(int anzahlReihen, int anzahlSitzeProReihe)
    {
        assert anzahlReihen >= 0 : "Vorbedingung verletzt: anzahlReihen >= 0";
        assert anzahlSitzeProReihe >= 0 : "Vorbedingung verletzt: anzahlSitzeProReihe >= 0";

        // Alle vorhandenen Buttons etc. entfernen
        removeAll();

        // Neue Buttons fÃ¼r PlÃ¤tze erstellen
        _buttons = new JPlatzButton[anzahlReihen][anzahlSitzeProReihe];
        for (int reihe = 0; reihe < anzahlReihen; reihe++)
        {
            JLabel label = new JLabel("Reihe " + (reihe + 1) + ":");
            imGitterEinfuegen(label, 0, reihe);
            for (int sitz = 0; sitz < anzahlSitzeProReihe; sitz++)
            {
                JPlatzButton button = new JPlatzButton(new Platz(reihe, sitz));
                button.setMinimumSize(PLATZBUTTON_GROESSE);
                button.setPreferredSize(PLATZBUTTON_GROESSE);
                imGitterEinfuegen(button, sitz + 1, reihe);
                button.addActionListener(_buttonListener);
                _buttons[reihe][sitz] = button;
            }
        }
        revalidate();
        repaint();

        // Nach der Ã„nderung ist kein Platz ausgewÃ¤hlt
        _ausgewaehltePlaetze.clear();
        informiereSelectionListener(_ausgewaehltePlaetze);
    }

    /**
     * FÃ¼gt ein GUI-Element in das Darstellungsgitter ein.
     * 
     * @param component Das GUI-Element, das eingefÃ¼gt werden soll.
     * @param gridx x-Position im Gitter.
     * @param gridy y-Position im Gitter.
     */
    private void imGitterEinfuegen(Component component, int gridx, int gridy)
    {
        add(component, new GridBagConstraints(gridx, gridy, 1, 1, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
                        2, 2, 2, 2), 0, 0));
    }

    /**
     * Gibt die Menge der ausgewÃ¤hlten PlÃ¤tze zurÃ¼ck.
     * 
     * @ensure result != null
     */
    public Set<Platz> getAusgewaehltePlaetze()
    {
        return new HashSet<Platz>(_ausgewaehltePlaetze);
    }

    /**
     * Entfernt die Auswahl, sodass keine PlÃ¤tze mehr ausgewÃ¤hlt sind.
     */
    public void entferneAuswahl()
    {
        for (Platz platz : _ausgewaehltePlaetze)
        {
            _buttons[platz.getReihe()][platz.getSitz()].setAusgewaehlt(false);
        }
        _ausgewaehltePlaetze.clear();
        repaint();
    }

    /**
     * Markiert den angegebenen Platz als verkauft.
     * 
     * @param platz der Platz.
     * 
     * @require platz != null
     */
    public void markierePlatzAlsVerkauft(Platz platz)
    {
        assert platz != null : "Vorbedingung verletzt: platz != null";
        _buttons[platz.getReihe()][platz.getSitz()].setVerkauft(true);
        repaint();
    }

    /**
     * Markiert den angegebenen Platz als frei.
     * 
     * @param platz der Platz.
     * 
     * @require platz != null
     */
    public void markierePlatzAlsFrei(Platz platz)
    {
        assert platz != null : "Vorbedingung verletzt: platz != null";
        _buttons[platz.getReihe()][platz.getSitz()].setVerkauft(false);
        repaint();
    }

    private static final long serialVersionUID = 4269824971779004365L;
}
