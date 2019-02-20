

------------------------------------------------------------------------------------------------------------------------------------
Update Tool User Guide

1. Add the configuration file to the /config/ project abbreviation .xml; configure the middleware directory to find the file; configure the file type (suffix) that needs to be updated.
2. Configure the modification time (add the modified file after this time to the update list); add the time in the /ctrl/lasttime.properties file.
3. Add the corresponding operation to the run.bat file.

Note: The names configured in the above three items must be consistent.

----------------------------------------------------------------------------------------------------------------------------------------

run : java ctrl.FileSearch jy_zq
