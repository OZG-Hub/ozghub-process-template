# OZG-Hub Process Template

This repository serves as a template for OZG-Hub processes. It includes all necessary dependencies to create your own process. To do so, follow the steps in the section [Creating your own process](#Creating-your-own-process).

## Prerequisites

* [ ] Ensure you're using java in version 11+: Enter `java --version` into a terminal and check the output.

## Creating your own process

* [ ] Close your IDEs (like IntelliJ), as they would otherwise automatically modify config files
* [ ] Create a new repository with the host of your choice (GitHub, GitLab, Bitbucket, ...) for your new project.
* [ ] Clone this newly created project, add a `README.md` file and do an initial commit
* [ ] Add this template as a new remote (suggested name: `template`) to your new repo: `git remote add template https://github.com/OZG-Hub/ozghub-process-template.git`
* [ ] Download all files from the new remote: `git fetch --all`
* [ ] Merge the template files: `git merge template/master --allow-unrelated-histories` 
* [ ] Ensure that the `README.md` you've created, and the one from this template no longer have any conflicts. You probably want to remove the introduction and the "Creating your own process" section.
* [ ] Commit the merge of the template project: `git commit` 
* [ ] Edit the project name in `settings.gradle` (use the `<customerNameCamelCase>-<processNameCamelCase>` format). You can now open your IDEs again.
* [ ] Rename the process model file (`./models/customerNameProcessName.bpmn`) 
* [ ] Open the process model file with the "Activiti Eclipse BPMN 2.0 Designer", and rename the process ID. Use this format: `m<MandantenId>.<customerName><processName>` 
* [ ] Rename the subfolder in `./scripts/` to use the same name as for the process ID.
* [ ] Commit the changes. 
* [ ] `git push` the changes.

## Editing the process model

Use the "Activiti Eclipse BPMN 2.0 Designer" Eclipse plugin.

## Editing forms

1. Use `./gradlew -Penvironment=sbw-dev startLocalHttpServer` to launch a local server that allows you to edit the forms. The server's IP & Port will be printed to the terminal.
1. After saving the changes, open the edited form JSON file and reformat it with your tool of choice (like IntelliJ IDEA). This ensures an useful diff between different commits.

## Building and deploying the process model

The build pipeline is managed via gradle (and the included gradle wrapper `gradlew`).

1. If you use Unit tests, run them all: `./gradlew test` and fix any issues.

1. Store the deployment configuration in environment variables for convenience. (e.g. by setting the via `OZGH_URL="xxx"; OZGH_USERNAME="xxx"; OZGH_PASSWORD="xxx"`)

1. Undeploy all existing forms and process models:

   1. Get a list of all processes and models:

      ```bash
      ./gradlew listP listF -Purl="$OZGH_URL" -Puser="$OZGH_USERNAME" -Ppassword="$OZGH_PASSWORD"
      ```

   1. Note which of these process models and forms will be replaced by your later deployment and note their `deploymentId`

   1. For each form: undeploy that form:

      ```bash
      ./gradlew undeployF --Purl="$OZGH_URL" -Puser="$OZGH_USERNAME" -Ppassword="$OZGH_PASSWORD" -PdeploymentId=REPLACE_ME_WITH_ID_FROM_PREVIOUS_STEP
      ```

   1. for each process model (there usually is only one): undeploy that model:

      ```bash
      ./gradlew undeployP --Purl="$OZGH_URL" -Puser="$OZGH_USERNAME" -Ppassword="$OZGH_PASSWORD" -PdeploymentId=REPLACE_ME_WITH_ID_FROM_PREVIOUS_STEP -PdeleteProcessInstances=true
      ```

1. Build and deploy the new version: 

   ```bash
   ./gradlew buildModel deployP deployF -Purl="$OZGH_URL" -Puser="$OZGH_USERNAME" -Ppassword="$OZGH_PASSWORD" -PdeploymentName=REPLACE_ME_WITH_THE_PROCESS_NAME
   ```

   *(Note: The `deploymentName` parameter is not used anywhere else. It just needs to be a unique id)*

More details about the gradle plugin and the provided task can be found at: [GitHub -> Prozess-Deployment-Gradle-Plugin für den OZG-Hub](https://github.com/OZG-Hub/ozghub-prozess-gradle-plugin)

## Launching / Testing the process

After deployment (see [Building and deploying the process model](#Building-and-deploying-the-process-model)), the process can be found in the list of all deployed processes. This website is accessed by adding `/prozessstart` to the base URL of the relevant OZG-Hub instance.

## Process parameters

> #### Note
>
> After forking the process template repository, you may want to edit this section

This process uses the following process parameters that need to be configured in Jesaja:

| Name                | Type        | Description                                                  | Default value               |
| ------------------- | ----------- | ------------------------------------------------------------ | --------------------------- |
| `e.g. sftpPassword` | e.g. String | e.g. "The password to authenticate to the configured SFTP server" | `e.g. CZ7uAmLcatvk5wLvdYjC` |

> ##### Warning
>
> The process parameters feature for OZG-Hub is currently under development and not useable yet.

> 