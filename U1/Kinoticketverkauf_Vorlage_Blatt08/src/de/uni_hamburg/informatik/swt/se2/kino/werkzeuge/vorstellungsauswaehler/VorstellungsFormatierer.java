package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.vorstellungsauswaehler;

import de.uni_hamburg.informatik.swt.se2.kino.materialien.Vorstellung;

/**
 * Formatierer fÃ¼r eine {@link Vorstellung}.
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
class VorstellungsFormatierer
{
    private Vorstellung _vorstellung;

    /**
     * Initialisiert einen Formatierer fÃ¼r die angegebene Vorstellung.
     * 
     * @param vorstellung
     *            die Vorstellung, die von diesem Formatierer dargestellt wird.
     */
    public VorstellungsFormatierer(Vorstellung vorstellung)
    {
        _vorstellung = vorstellung;
    }

    /**
     * Gibt die Vorstellung zurÃ¼ck, die von diesem Formatierer dargestellt wird.
     */
    Vorstellung getVorstellung()
    {
        return _vorstellung;
    }

    @Override
    public String toString()
    {
        return _vorstellung.getAnfangszeit().getFormatiertenString() + " - "
                + _vorstellung.getFilm().getFormatiertenString() + ", "
                + _vorstellung.getKinosaal().getName();
    }
}
