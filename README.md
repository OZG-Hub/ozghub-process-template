# OZG-Hub Process Template

TODO: Major refactoring of the readme file. Should Mainly include licence and contribution infos and link to another file with the actual user guide in German. 

This repository serves as a template for OZG-Hub processes (also called "Onlinedienste" in this context). It includes all necessary dependencies to create your own process. To do so, follow the steps in the section [Creating your own process](#Creating-your-own-process).

## Prerequisites

* [ ] [Eclipse IDE](https://www.eclipse.org/eclipseide/) with installed [Activiti Eclipse BPMN 2.0 Designer](https://www.activiti.org/designer/update/) plugin
* [ ] Java version 11: Enter `java --version` into a terminal and check the output. Newer versions will not work.
* [ ] (optional) Another IDE to edit groovy script files. We recommend [IntelliJ IDEA](https://www.jetbrains.com/idea/)

## Creating your own process

* [ ] Close your IDEs (like IntelliJ), as they would otherwise automatically modify config files
* [ ] Create a new repository with the host of your choice (GitHub, GitLab, Bitbucket, ...) for your new project
* [ ] Clone this newly created project, add a `README.md` file and do an initial commit
* [ ] Add this template as a new remote (suggested name: `template`) to your new repo: `git remote add template https://github.com/OZG-Hub/ozghub-process-template.git`
* [ ] Download all files from the new remote: `git fetch --all`
* [ ] Merge the template files: `git merge template/master --allow-unrelated-histories` 
* [ ] Ensure that the `README.md` you've created, and the one from this template no longer have any conflicts. You probably want to remove the introduction for the template repository and the "Creating your own process" section. Additionally, you might want to update the "Process parameters" section
* [ ] Commit the merge of the template project
* [ ] Edit the project name in `settings.gradle` and in `.idea/.name` (use the `<customerNameCamelCase>-<processNameCamelCase>` format). You can now open your IDEs again
* [ ] Rename the process model file (`./models/customerNameProcessName.bpmn`). Use the same format as above, but remove dashes `-`. 
* [ ] Open the project in Eclipse ("File" --> "Open Projects from File system") and then open the process model file with the "Activiti Eclipse BPMN 2.0 Designer" and rename the process ID. Use this format: `m<MandantenId>.<customerName><processName>` 
* [ ] Also update the `Name` attribute in the same window to show a user-friendly name of the process. This will later be seen on the page listing all available processes
* [ ] Rename the subfolder in `./scripts/` to use the same name as for the process ID. Do **not** include the "Mandanten-ID" or the dot `.`. I.e.: `scripts/<processIdFromBpmnWithoutMandantenIdOrDot>/<idOfTheScriptTask>.groovy`.
* [ ] Commit the changes
* [ ] `git push` the changes

## Editing the process model

Use the "Activiti Eclipse BPMN 2.0 Designer" Eclipse plugin.

## Editing forms
This template includes an example for a form in the `./forms` subfolder. You can rename and reuse this file. After renaming it, you also need to change the `id` attribute inside the file. Don't forget to also change the "form key" attribute in the bpmn file.

1. Use `./gradlew startLocalHttpServer` to launch a local server that allows you to edit the forms. The server's IP & Port will be printed to the terminal.
2. After saving the changes, open the edited form JSON file and reformat it with your tool of choice (like IntelliJ IDEA). This ensures a useful diff between different commits.
3. You can stop the server via a 'Server beenden' Button on the webpage.

## Building and deploying the process model

The build pipeline is managed via gradle (and the included gradle wrapper `gradlew`).

1. If you use Unit tests, run them all: `./gradlew test` Fix any issues if there are failing tests.
1. Store the deployment configuration in environment variables for convenience. (e.g. by setting them via `OZGH_URL="xxx"; OZGH_USERNAME="xxx"; OZGH_PASSWORD="xxx"`) (You might want to use the auto-type feature of your password manager.)
1. Build and deploy your process model and forms (possibly overwriting existing files with the same id): 
   ```bash
   ./gradlew buildModel deployP deployF -Purl="$OZGH_URL" -Puser="$OZGH_USERNAME" -Ppassword="$OZGH_PASSWORD" -PduplicateProcesskeyAction="UNDEPLOY" -PversionName="v1.0-SNAPSHOT" -PdeploymentName="DeploymentFromLocalRepository" 
   ```

   *(Note: The `deploymentName` parameter is used to identify a specific deployment of a process model. The `versionName` parameter is used to specify the version of the processmodel.)*

More details about the gradle plugin and the provided task can be found at: [GitHub -> Prozess-Deployment-Gradle-Plugin für den OZG-Hub](https://github.com/OZG-Hub/ozghub-prozess-gradle-plugin)

## Launching / Testing the process

After deployment (see [Building and deploying the process model](#Building-and-deploying-the-process-model)), the process can be found in the list of all deployed processes. This website is accessed by adding `/prozessstart` to the base URL of the relevant OZG-Hub instance.

## Process parameters

> **Note**
>
> This section can be used to document the parameters of your process. The actual configuration in
> the Jesaja system cannot be done by a process developer. Please email ozg-hub@seitenbau.com and 
> include the table of your processes.

This process uses the following process parameters that need to be configured in Jesaja:

| Name                | Type        | Description                                                       | Default value               |
|---------------------|-------------|-------------------------------------------------------------------|-----------------------------|
| `e.g. sftpPassword` | e.g. String | e.g. "The password to authenticate to the configured SFTP server" | `e.g. CZ7uAmLcatvk5wLvdYjC` |
