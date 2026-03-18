# Informatie
Deze ImageJ macro wordt gebruikt om bestanden uit verschillende mappen te verzamelen en automatisch klaar te zetten voor de readers in een gestandaardiseerde mappenstructuur.  
De macro biedt de mogelijkheid om te kiezen tussen:  
* hires visual read (vr_hires)  
* earl visual read (vr_earl)  

Op basis van deze keuze worden de juiste bestanden opgezocht en gekopieerd naar de juiste locaties.  

**Belangrijk**  
* De macro wijzigt geen bestanden in de originele (bron)map.  
* Bestanden worden uitsluitend gekopieerd naar de outputmap.  
* Bestaande bestanden in de outputmap worden niet overschreven.  
* Het is mogelijk om de macro meerdere keren te draaien om extra bestanden toe te voegen aan een bestaande structuur.  

**Toekomstige uitbreiding**  
Deze versie bevat nog geen functionaliteit voor TAU-SPEX PDF-bestanden.  
Deze zal worden toegevoegd zodra de PDF’s aanwezig zijn in de mappenstructuur.  

# Mappenstructuur en bestanden
**Hires bestanden**  
De volgende bestanden worden verzameld en geplaatst:  

VR-bestanden (.vpx):
* sub-TAP01-0XX_pet_mcc_hires  
* sub-TAP01-0XX_pet_vr_hires  
  * Worden geplaatst in:  
    `BL/pet/processed/vr_hires`

Vinci PET image (.nii):  
* sub-TAP01-0XX_pet_rec-acdyn_brain_hires_20_sumall
  * Wordt geplaatst in:  
    `BL/pet/raw/sub-TAP01-0XX_pet_rec-acdyn_totalbody_hires_20`

MR2PET image (.nii):  
* sub-TAP01-0XX_mr2pet_hires
  * Wordt geplaatst in:  
    `BL/anat/processed/mr2pet_hires`  


**Earl bestanden**  
Voor EARL-bestanden wordt dezelfde structuur gebruikt:  
* VR-bestanden (.vpx):  
    `BL/pet/processed/vr_earl`
* Vinci PET image (.nii):  
    `BL/pet/raw/sub-TAP01-0XX_pet_rec-acdyn_totalbody_earl2_20`
* MR2PET image (.nii):  
    `BL/anat/processed/mr2pet_earl`

# Hoe te gebruiken  
1. Open ImageJ.  
2. Ga naar Plugins → New → Macro.
3. Kopieer de code van create_folder_structure.java en plak deze in het venster.
4. Klik op Save en sla de macro op in de standaard macro-map van ImageJ.  
   Geef een duidelijke naam, bijvoorbeeld: create_folder_structure
5. Start de macro via: Plugins → Macros → Run en selecteer de macro.  
6. Volg de instructies in de pop-ups (selecteer mappen, kies type, voer subjecten in).  

Voor extra uitleg over de werking van de code, zie de comments in het script.  

