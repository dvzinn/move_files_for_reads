// ---- SETTINGS ----

// Ask user to select the base folder (where all subject folders are located)
base = getDirectory("Select base folder (AV1451_TAP_TAU)");

// If no folder is selected, stop the script
if (base == "") {
    print("No base folder selected.");
    exit();
}

// Ask user to select the output folder (where new BL structure will be created)
out = getDirectory("Select OUTPUT folder");

// If no output folder is selected, stop the script
if (out == "") {
    print("No output folder selected.");
    exit();
}

// ---- CHOOSE TYPE ----
// Create a dialog window for selecting the type (hires or earl)
Dialog.create("Select type");
// Add dropdown options to the dialog
Dialog.addChoice("Type:", newArray("vr_hires", "vr_earl"), "vr_hires");
// Show the dialog to the user
Dialog.show();
// Store the selected type (vr_hires or vr_earl)
type = Dialog.getChoice();

// ---- CREATE FOLDER STRUCTURE ----
// Create folders inside output directory
File.makeDirectory(out + "BL/");
File.makeDirectory(out + "BL/pet/");
File.makeDirectory(out + "BL/pet/processed/");
File.makeDirectory(out + "BL/pet/processed/" + type + "/");
File.makeDirectory(out + "BL/pet/raw/");
File.makeDirectory(out + "BL/anat/");
File.makeDirectory(out + "BL/anat/processed/");

// Determine which MR2PET folder to use based on selected type
if (type == "vr_hires")
    mrfolder = "mr2pet_hires/";
else
    mrfolder = "mr2pet_earl/";

// Create MR2PET output folder
File.makeDirectory(out + "BL/anat/processed/" + mrfolder);

// ---- SELECT SUBJECTS ----
// Ask user to input subject IDs separated by commas
subjects = getString("Enter subjects (comma separated):", "");
// Split the input string into an array of subjects
sublist = split(subjects, ",");

// ---- LOOP OVER SUBJECTS ----
// Loop over each subject entered by the user
for (s = 0; s < sublist.length; s++) {
    // Get current subject ID
    subject = sublist[s];
    // Initialize flags to track whether files are found
    foundVR = false;
    foundSumall = false;
    foundMR = false;
  
    // ---- DEFINE PATHS ----
    // Path to VR files (processed PET)
    vrdir = base + subject + "/AV1451/BL/pet/processed/" + type + "/";
    // Path to raw PET data depends on selected type
    if (type == "vr_hires")
        petdir = base + subject + "/AV1451/BL/pet/raw/" + subject + "_pet_rec-acdyn_totalbody_hires_20/";
    else
        petdir = base + subject + "/AV1451/BL/pet/raw/" + subject + "_pet_rec-acdyn_totalbody_earl2_20/";
    // Path to MR2PET files
    mrdir = base + subject + "/AV1451/BL/anat/processed/" + mrfolder;

    // ---- COPY VR FILES ----
    // Check if VR folder exists
    if (!File.exists(vrdir)) {
        print("VR folder NOT FOUND for " + subject);
    } else {
        // Get list of files in VR folder
        list = getFileList(vrdir);
        // Loop over files in VR folder
        for (i = 0; i < list.length; i++) {
            name = list[i];
            // Select only pet_mcc and pet_vr files with .vpx extension
            if ((indexOf(name, "pet_mcc") != -1 || indexOf(name, "pet_vr") != -1)
                && endsWith(name, ".vpx")) {
                // Mark that VR files were found
                foundVR = true;
                // Define destination path
                dest = out + "BL/pet/processed/" + type + "/" + name;
                // Copy file only if it does not already exist
                if (!File.exists(dest)) {
                    File.copy(vrdir + name, dest);
                    print("Copied VR: " + name);
                } else {
                    print("Skipped VR (exists): " + name);
                }
            }
        }
        // If no VR files were found, print warning
        if (!foundVR)
            print("WARNING: No VR files found for " + subject);
    }

    // ---- COPY PET SUMALL FILE ONLY ----
    // Check if PET folder exists
    if (!File.exists(petdir)) {
        print("PET folder NOT FOUND for " + subject);
    } else {
        // Get list of files in PET folder
        petlist = getFileList(petdir);
        // Determine folder suffix based on type
        if (type == "vr_hires")
            folderSuffix = "hires_20";
        else
            folderSuffix = "earl2_20";

        // Create subject-specific PET output folder
        subjectPetFolder = out + "BL/pet/raw/" + subject + "_pet_rec-acdyn_totalbody_" + folderSuffix + "/";
        File.makeDirectory(subjectPetFolder);
        // Loop over PET files
        for (i = 0; i < petlist.length; i++) {
            name = petlist[i];
            // Select only sumall NIfTI file
            if (indexOf(name, "sumall") != -1 && endsWith(name, ".nii")) {
                // Mark that sumall file was found
                foundSumall = true;
                // Define destination path
                dest = subjectPetFolder + name;
                // Copy file only if it does not already exist
                if (!File.exists(dest)) {
                    File.copy(petdir + name, dest);
                    print("Copied SUMALL PET: " + name);
                } else {
                    print("Skipped SUMALL PET (exists): " + name);
                }
            }
        }
        // If no sumall file found, print warning
        if (!foundSumall)
            print("WARNING: sumall.nii NOT FOUND for " + subject);
    }

    // ---- COPY MR2PET FILES ----
    // Print MR directory for debugging
    print("Checking MR folder: " + mrdir);
    // Check if MR folder exists
    if (!File.exists(mrdir)) {
        print("MR2PET folder NOT FOUND for " + subject);
    } else {
        // Get list of MR files
        mrlist = getFileList(mrdir);
        // Loop over MR files
        for (i = 0; i < mrlist.length; i++) {
            name = mrlist[i];
            // Select only NIfTI files
            if (endsWith(name, ".nii")) {
                // Mark that MR file was found
                foundMR = true;
                // Define destination path
                dest = out + "BL/anat/processed/" + mrfolder + name;
                // Copy file only if it does not already exist
                if (!File.exists(dest)) {
                    File.copy(mrdir + name, dest);
                    print("Copied MR: " + name);
                } else {
                    print("Skipped MR (exists): " + name);
                }
            }
        }

        // If no MR files found, print warning
        if (!foundMR)
            print("WARNING: No MR2PET .nii file found for " + subject);
    }

    // ---- SUBJECT SUMMARY ----
    // Check if all required files were found
    if (foundVR && foundSumall && foundMR)
        print("SUCCESS: " + subject);
    else
        print("INCOMPLETE: " + subject);
    // Separator line for readability in log
    print("----");
}

// ---- FINISHED ----
// Print final message when script completes
print("Done.");
