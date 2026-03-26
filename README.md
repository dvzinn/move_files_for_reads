# Informatie
Deze ImageJ macro wordt gebruikt om bestanden uit verschillende mappen te verzamelen en automatisch klaar te zetten voor de readers in een gestandaardiseerde mappenstructuur.  
De macro biedt de mogelijkheid om te kiezen tussen:   
* hires visual read (vr_hires)
* earl visual read (vr_earl)
* hires TAU-SPEX (tauspex_hires)
* earl TAU-SPEX (tauspex_earl)

Op basis van deze keuze worden de juiste bestanden opgezocht en gekopieerd naar de juiste locaties.  
Bij selectie van een TAU-SPEX optie worden automatisch ook de bijbehorende PDF-bestanden meegenomen.  

**Belangrijk**  
* De macro wijzigt geen bestanden in de originele (bron)map.  
* Bestanden worden uitsluitend gekopieerd naar de outputmap.  
* Bestaande bestanden in de outputmap worden niet overschreven.  
* Het is mogelijk om de macro meerdere keren te draaien om extra bestanden toe te voegen aan een bestaande structuur.
* De macro geeft per subject aan of alle benodigde bestanden aanwezig zijn (SUCCESS / INCOMPLETE).  
* Aan het einde wordt een samenvatting gegeven van het aantal succesvolle en incomplete subjects.  

# Mappenstructuur en bestanden
**Hires bestanden**  
De volgende bestanden worden verzameld en geplaatst:  

VR-bestanden (.vpx):
* sub-TAP0X-0XX_pet_mcc_hires  
* sub-TAP0X-0XX_pet_vr_hires  
  * Worden geplaatst in:  
    `BL/pet/processed/vr_hires`

TAU-SPEX PDF (alleen bij tauspex_hires)
* sub-TAP0X-0XX_pet_tauspex_hires.pdf  
  * Wordt geplaatst in:  
    `BL/pet/processed/vr_hires`

Vinci PET image (.nii):  
* sub-TAP0X-0XX_pet_rec-acdyn_brain_hires_20_sumall
  * Wordt geplaatst in:  
    `BL/pet/raw/sub-TAP0X-0XX_pet_rec-acdyn_totalbody_hires_20`

MR2PET image (.nii):  
* sub-TAP0X-0XX_mr2pet_hires
  * Wordt geplaatst in:  
    `BL/anat/processed/mr2pet_hires`  


**Earl bestanden**  
Voor EARL-bestanden wordt dezelfde structuur gebruikt:  
* VR-bestanden (.vpx):  
    `BL/pet/processed/vr_earl`
* TAU-SPEX PDF (alleen bij tauspex_earl)  
    `BL/pet/processed/vr_earl`
* Vinci PET image (.nii):  
    `BL/pet/raw/sub-TAP0X-0XX_pet_rec-acdyn_totalbody_earl2_20`
* MR2PET image (.nii):  
    `BL/anat/processed/mr2pet_earl`

# Hoe te gebruiken  
1. Open ImageJ.  
2. Ga naar Plugins → New → Macro.
3. Kopieer de code van create_folder_structure.java en plak deze in het venster.
4. Klik op Save en sla de macro op in de standaard macro-map van ImageJ.  
   Geef een duidelijke naam, bijvoorbeeld: create_folder_structure
5. Start de macro via: Plugins → Macros → Run en selecteer de macro.  
6. Volg de instructies in de pop-ups (selecteer mappen, kies type (vr_hires, vr_earl, tauspex_hires, tauspex_earl), voer subjecten in (comma separated)).  

# Output en controle
Tijdens het uitvoeren:  
* Per bestand wordt aangegeven:
  * gekopieerd
  * overgeslagen (bestaat al)
  * niet gevonden
* Per subject:
  * SUCCESS → alle benodigde bestanden aanwezig
  * INCOMPLETE → één of meerdere bestanden ontbreken

**Eindoverzicht**     
Na afloop toont de macro: 

```
==== SUMMARY ====  
Total subjects: X  
Successful: X  
Incomplete: X  
Done.
```

Voor extra uitleg over de werking van de code, zie de comments in het script.  

