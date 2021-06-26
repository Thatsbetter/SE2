package mediathek.services;

import java.io.FileWriter;
import java.io.IOException;

import mediathek.materialien.Verleihkarte;

public class VerleihProtokollierer
{
    public static final String RUECKGABE = "Rueckgabe";
    public static final String AUSLEIHE = "Ausleihe";

    /*
     * @param ereignis Ein String der beschreibt was an wenn wann verliehen oder zurÃ¼ckgenommen wurde.
     * 
     * @require ereignis.equals(RUECKGABE)  ||  ereignis.equals(AUSLEIHE) 
     * @require ereignis != null
     */
    public static void protokolliere(String ereignis, Verleihkarte verleihkarte)
            throws ProtokollierException

    {
        assert (ereignis.equals(AUSLEIHE) || ereignis.equals(
                RUECKGABE)) : "Vorbedingung verletzt (ereignis.equals(AUSLEIHE) || ereignis.equals(RUECKGABE))";
        assert ereignis != null : "Vorbedingung verletzt ereignis != null";

        try (FileWriter filewriter = new FileWriter("./bestand/protokoll.txt",
                true))
        {

            filewriter.write(
                    ereignis + ": \n" + verleihkarte.getFormatiertenString());

            //                        System.out.println(
            //                                ereignis + ": \n" + verleihkarte.getFormatiertenString());
        }

        catch (IOException e)
        {
            throw new ProtokollierException();
        }
        finally
        {
            System.out.println("Protokolliervorgang beendet");
        }

    }

}
