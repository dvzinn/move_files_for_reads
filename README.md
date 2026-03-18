# Informatie
ImageJ macro om bestanden uit mapjes te halen en klaar te zetten voor de readers. 
Deze versie bevat nog geen optie om automatisch de TAU-SPEX PDF files te verplaatsen naar de mapjes. Dit wordt toegevoegd wanneer duidelijk is waar deze vandaan gehaald kunnen worden.
De macro geeft de optie om te kiezen tussen hires afbeeldingen of earl bestanden en kopieert dan de benodigde bestanden binnen de paden.

De benodigde paden voor de hires bestanden:
  sub-TAP01-0XX_pet_mcc_hires en sub-TAP01-0XX_pet_vr_hires worden geplaatst in:
    BL\pet\processed\vr_hires

  Vinci Image (NIfTI) (sub-TAP01-0XX_pet_rec-acdyn_brain_hires_20_sumall):
  
    BL\pet\raw\sub-TAP01-0XX_pet_rec-acdyn_totalbody_hires_20
  
  Vinci Image (NIfTI) (sub-TAP01-0XX_mr2pet_hires):
  
    BL\anat\processed\mr2pet_hires

Binnen dezelfde mappenstructuur worden de earl bestanden geplaatst:

    BL\pet\processed\vr_earl
    BL\pet\raw\sub-TAP01-0XX_pet_rec-acdyn_totalbody_earl2_20
    BL\anat\processed\mr2pet_earl
  

# Hoe te gebruiken
1. Open ImageJ.
2. Klik op Plugins > New > Macro.
3. Kopieer de create_folder_structure.java code en plak het in het venster.
4. Klik op opslaan of save. Zorg bij het opslaan ervoor dat de macro opgeslagen wordt in de al bestaande macromap binnen de ImageJ-map. Hier kom je waarschijnlijk automatisch al terecht.
   Geef de macro een herkenbare naam: bijvoorbeeld "create_folder_structure" en klik op opslaan. 
6. Om de macro te openen, ga naar ImageJ en klik op Plugins > Macros > Run, en klik op "create_folder_structure", of een andere naam indien de macro anders is genoemd.
7. De macro start nu vanzelf. De instructies staan in de werkbalk. Voor meer informatie, zie de comments in het script.
