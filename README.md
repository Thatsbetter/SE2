# SE2 - 8
 SE2 8.Aufgabe

Hier findest du die 8.Aufgabe von SE2 SoSe21

<br>
<p>
 
<b> UML </b>:
https://lucid.app/lucidchart/2bcf6f82-7804-4b56-8dfb-75c3f7fd3773/edit?page=0_0#
<p>

Aufgabenverteilung:

L: + BarzahlungsWerkzeug(preis : int)

L: - istBetragGueltig(s : String): boolean
 
L: - restBetragBerechnen(b : int): int
 
L: - restBetragAendern(b: int ):void


T: - meldeFehler(): void 
 
T: - registriereUIAktionen() : void

T: -abbrechenBtnGedrueckt : void
 
T: -okBtnGedrueckt : void 

T: -setzeOKButtonAktiv(boolean aktiv):void


?: -warVerkaufErfolgreich():boolean