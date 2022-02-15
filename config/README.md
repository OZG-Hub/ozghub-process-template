# About the `./config` folder

This folder contains some configuration data for editing forms and uploading process models. Most of
the settings are silently ignored. However, the `prozess-pipeline-gradle-plugin` plugin still 
requires a (any) value to be there. Since this plugin can not be updated to remove this unnecessary
check, placeholder values have to remain.

The only value that matters is the `url` attribute in `./config/default.json` as it controls which
Serviceportal instance hosts the form editor. 
