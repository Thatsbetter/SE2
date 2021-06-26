package mediathek.werkzeuge;
/**
 * Basisklasse fÃ¼r Subwerkzeuge, die ihr Kontextwerkzeug bei Ã„nderungen
 * benachrichtigen mÃ¶chten.
 * 
 * Diese Klasse implementiert die Schnittstelle, Ã¼ber die sich Beobachter an dem
 * Subwerkzeug registrieren kÃ¶nnen. In der Regel wird es genau ein beobachtendes
 * Kontextwerkzeug geben. In Ausnahmen koennen es auch mehrere sein, wenn
 * indirekte Kontextwerkzeuge ebenfalls beobachten. Diese Klasse erlaubt beides.
 * 
 * Erbende Klassen rufen die Methode #informiereUeberAenderung() auf, um alle
 * Beobachter zu benachrichtigen. Erbende Klassen mÃ¼ssen dokumentieren, in
 * welchen FÃ¤llen sie ihre Beobachter informieren.
 * 
 * Diese Klasse entspricht der Klasse "Beobachtbar" im Beobachter-Muster.
 * 
 * @author SE2-Team
 * @version SoSe 2021
 */
interface SubWerkzeugObserver
{
    /**
     * Benachrichtigt diesen Beobachter bei einer Ã„nderung in dem beobachteten
     * Subwerkzeug.
     */
    void informiereUeberAenderung();
}
