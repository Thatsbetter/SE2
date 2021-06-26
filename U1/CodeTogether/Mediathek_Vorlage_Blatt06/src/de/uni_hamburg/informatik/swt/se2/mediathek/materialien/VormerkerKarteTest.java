package de.uni_hamburg.informatik.swt.se2.mediathek.materialien;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Kundennummer;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.CD;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;

public class VormerkerKarteTest
{
    private Kunde _kunde1;
    private Kunde _kunde2;
    private Kunde _kunde3;
    private Kunde _kunde4;
    private Medium _medium;
    private VormerkerKarte _karte;

    public VormerkerKarteTest()
    {
        _kunde1 = new Kunde(new Kundennummer(123456), "ich", "du");
        _kunde2 = new Kunde(new Kundennummer(987654), "er", "sie");
        _kunde3 = new Kunde(new Kundennummer(314159), "p", "i");
        _kunde4 = new Kunde(new Kundennummer(314359), "J", "B");

        _medium = new CD("bar", "baz", "foo", 123);

        _karte = new VormerkerKarte(_kunde1, _medium);
    }

    @Test
    public void testeKonstruktor() throws Exception
    {
        assertEquals(_kunde1, _karte.getVormerker(0));
        assertEquals(_medium, _karte.getMedium());
    }

    @Test
    public void testGetVormerker()
    {
        assertEquals(_kunde1, _karte.getVormerker(0));
    }

    @Test
    public void testGetMedium()
    {
        assertEquals(_medium, _karte.getMedium());
    }

    @Test
    public void testGetAktuelleListenLaenge()
    {
        assertEquals(1, _karte.getAktuelleListenLaenge());
    }

    @Test
    public void testAddVormerker()
    {
        _karte.addVormerker(_kunde1);
        assertEquals(1, _karte.getAktuelleListenLaenge());

        _karte.addVormerker(_kunde2);
        assertEquals(_kunde2, _karte.getVormerker(1));

        _karte.addVormerker(_kunde3);
        assertEquals(_kunde3, _karte.getVormerker(2));

        _karte.addVormerker(_kunde4);
        assertEquals(3, _karte.getAktuelleListenLaenge());
    }

    @Test
    public void testRueckeVor()
    {
        _karte.addVormerker(_kunde2);
        _karte.rueckeVor();
        assertEquals(_kunde2, _karte.getVormerker(0));

        _karte.rueckeVor();
        assertEquals(null, _karte.getVormerker(0));
    }
}
