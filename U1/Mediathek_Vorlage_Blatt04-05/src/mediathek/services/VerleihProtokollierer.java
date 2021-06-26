package mediathek.services;
import java.io.FileWriter;
import java.io.IOException;

import mediathek.materialien.Verleihkarte;

public class VerleihProtokollierer
{
    private static final String _fileName = "./protokoll.txt";
    //private static FileWriter _fw;

    /**
     * 
     * @param ereignis ,das protokolliert werden soll
     * @param verleihkarte , das dieses Protokoll betrifft
     * 
     */

    public static void protokolliere(String ereignis, Verleihkarte verleihkarte)
            throws ProtokollierException
    {
        assert ereignis != null;
        assert verleihkarte != null;

        System.out.println(ereignis + " " + verleihkarte.toString());

        try (FileWriter fw = new FileWriter(_fileName, true))
        {
            fw.write(ereignis + " " + verleihkarte.toString());

        }
        catch (IOException e)
        {
            throw new ProtokollierException();

        }
        finally
        {
            System.out.println("Protokolliervorgang beendet.");
        }

    }

}
