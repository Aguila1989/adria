
# Adramed & ResQ

## Our concept

In the wake of the Global Disaster of 2064, humanity has faced unprecedented challenges, but we've built a better future, and we are entitled to make it even brighter. That’s why, we are presented with a unique opportunity to build a safer, more sustainable future.

Our startup, AdraMed, is on a mission to pioneer a personal medical assistant that will not only address the health and well-being of Adria's colonists but also extend its reach to explorers, healthcare, and individuals on every inhabited system.

Our flagship product, ResQ, that harnesses the power of cutting-edge technology, an advanced AI avatar called AVA (Advanced Virtual Aide), and medical innovations. <br>
Its features:
- Imagine a companion that continuously monitors your health, sending swift and accurate diagnoses, offering real-time insights and proactive recommendations for a longer, healthier life. Wellness is also primordial, and ResQ offers plans and recommendations for a better quality of life.
- In case of emergency, guidance is provided, and reports are sent to the nearest help.
- As for the pioneers among us, ResQ prepares you for your space travels.
- Harness the power of AVA to predict health trends and take preventive measures. Not only does our product save lives, it also improves them.

In conclusion, ResQ is more than a product; it's a holistic solution, a beacon of hope, and a testament to human ingenuity. Together, we will build a world where healthcare is not a luxury but a right, where technology empowers us to live healthier and happier lives.

main focus: data collection, early diagnosis with AI, predicting health trends

## Deliverables

### D1: Business Case including financial analysis

- [Business Case](https://docs.google.com/document/d/1INqB4XOsBnhdvSC3ndovVZl5vz3JS7PKV637Q-0dxpw/edit?usp=sharing)
- [BMC](https://miro.com/app/board/uXjVMjSM5mo=/?share_link_id=343167232358)

### D2: Marketing Website

- [Marketing website Adramed](https://kevinhenri.wixsite.com/home)

### D3: Schematics

- [flow charts](https://miro.com/app/board/uXjVNeCv5Eg=/?share_link_id=333735847172)
- [C4](https://miro.com/app/board/uXjVNeCJp-o=/?share_link_id=759508011663)
- [ERD](https://lucid.app/lucidchart/178d00ec-643c-4efc-8dea-f55c3c6839b8/edit?viewport_loc=-599%2C-72%2C2176%2C1059%2C0_0&invitationId=inv_31333d50-5996-44af-ada9-6711b9fc6e87)

### D4: Wireframes

- [Figma wireframe](https://www.figma.com/file/ufrxBzfAks51oPauPH0BRy/Wireframes-Adramed?type=design&node-id=0%3A1&mode=design&t=0bglj1aZBKnrf695-1)

### D5: POC

- in development
- [Feature list](feature_list.md)
### D6: Publicly accessible API

- [API draft](api_structure.md)

### D7: Readme

- this

### D8: PM work in GitLab

- see gitlab

### D9: Various peer assessments, introspections and reflections

- see leho

### D10: Presentation for non-technical jury

- WIP

## Using the Software

### Prerequisites

Before you begin, ensure that you have the following:
- A working Adria Wristband
- A valid Adria ID & Authenticator (consult local Identity Services if errors occur)
- A validated ResQ Account (can be created when using our application for the first time with the prerequisites above)

### Usage

Description per Webpage

#### Choose Plan:
This page lets a new user choose a subscription plan from ResQ, ResQ+ and ResQ Pro.
The freemium plan allows a user to access a limited version of our application with advertisement. 
ResQ+ has the same features as the freemium version but with additional functionalities. 
And the same goes for ResQ Pro that adds even more functionalities for a higher cost.

![choose plan](/images/flow/choosePlan1.png)

#### Confirm Plan:
When the user chose a plan, he gets directed to a confirmation page where he can check the plan's 
subscription amount monthly or yearly. He can review the features linked to the subscription plan again and confirm his choice.

![confirm plan](/images/flow/choosePlan2.png)

#### Full Dashboard:
This page allows the user to access the feature he has access to. 
Freemium users will only have a limited version and will only have access to "Stats" & "AI Diagnosis" for example.
Some additional links can be found in this page that lead to "Settings", "Emergency Guidance" & "Emergency Alert".

![dashboard](/images/flow/fullDashboard.png)

#### Metrics:
Our metrics page allows users that see their health statistics live.
Stats such as "BPM" & "Saturation" are included in the Freemium plan as the rest can be overviewed with paid subscriptions.

![metrics](/images/flow/metrics.png)

#### Timeline:
As the name implies, our "Timeline" page displays the graphics for each of the users' health metrics.
These can be used to detect certain patterns and time frames where the user had health issues.

![metrics](/images/flow/timeline.png)

#### AI Diagnosis:
The "AI Diagnosis" page is where AvA really shines, she will take your symptoms into account and match them to a disease. 
Your obvious and testable symptoms are automatically stored and the user can manually add more.

![diagnosis](/images/flow/diagnosis.png)

#### Personal History:
The "Personal History" page goes with the AI Diagnosis, as it tracks and stores all your recorded diagnosis.
Not only does it store AvA generated files but doctors can also add their own diagnosis.

![history](/images/flow/personalHistory.png)

#### Settings:
As the name implies, "Settings" is the page where users can modify your account settings. 
The main accessible setting is "Subscription", where users can modify their plan.
On top of that, Premium Users have access to a "Customization" setting where they can change AvA's skin.

![settings](/images/flow/settings.png)

#### Emergency Guidance:
AvA is also able to detect emergency situations and give valuable insight and advices. 
Every situation is accounted for, and she can guide users to same themselves or others.

![guidance](/images/flow/emergencyGuidance.png)

#### Emergency Alert:
On top of guiding users, AvA is also able to contact the closest emergency services. 
After a short amount of time (needed to confirm the severity of the emergency) medical services will be alerted.

![alert](/images/flow/emergencyAlert.png)

## Getting Started

### Online

- [ResQ](https://project-2.ti.howest.be/2023-2024/group-05/)

### Locally

#### Running the Client

Clone the repository: git clone git@gitlab.ti.howest.be:ti/2023-2024/s3/analysis-and-development-project/projects/group-05/client.git <br>

#### Running the Server

Clone the repository: git clone git@gitlab.ti.howest.be:ti/2023-2024/s3/analysis-and-development-project/projects/group-05/server.git <br>


## Code Quality

For an analysis of the project's codebase, visit SonarQube.
You can find our server analysis here: [Server Sonar](https://sonarqube.ti.howest.be/dashboard?id=2023.project-2%3Aadria-server-05). <br>
And the client analysis here: [Client Sonar](https://sonarqube.ti.howest.be/dashboard?id=2023.project-2%3Aadria-client-05).


### Client
[![Reliability Rating](https://sonarqube.ti.howest.be/api/project_badges/measure?project=2023.project-2%3Aadria-client-05&metric=reliability_rating&token=sqb_7106d961151e0ed7fae41b9d11e6990b93f6a510)](https://sonarqube.ti.howest.be/dashboard?id=2023.project-2%3Aadria-client-05)
[![Security Rating](https://sonarqube.ti.howest.be/api/project_badges/measure?project=2023.project-2%3Aadria-client-05&metric=security_rating&token=sqb_7106d961151e0ed7fae41b9d11e6990b93f6a510)](https://sonarqube.ti.howest.be/dashboard?id=2023.project-2%3Aadria-client-05)


### Server
[![Reliability Rating](https://sonarqube.ti.howest.be/api/project_badges/measure?project=2023.project-2%3Aadria-server-05&metric=reliability_rating&token=sqb_f8f1fd5cc9b124afdf256137044d1d41e57a31f9)](https://sonarqube.ti.howest.be/dashboard?id=2023.project-2%3Aadria-server-05)
[![Security Rating](https://sonarqube.ti.howest.be/api/project_badges/measure?project=2023.project-2%3Aadria-server-05&metric=security_rating&token=sqb_f8f1fd5cc9b124afdf256137044d1d41e57a31f9)](https://sonarqube.ti.howest.be/dashboard?id=2023.project-2%3Aadria-server-05)

## Repositories

### Server

The Server repository is organized as follows:
- *Folder:* conf (configuration JSON file)
- *Folder:* gradle/wrapper
- *Folder:* src (source code)
- *Folder:* src/main (main source code)
- *Folder:* src/test (tests)
- gradle properties
- gradle settings

### Client

The Client repository is organized as follows:
- *Folder:* reports (test reports xml files)
- *Folder:* scss (graphics css files)
- *Folder:* src (source code)
- sonar-project properties
- package.json

### Documentation

The Documentation repository is organized as follows:
- *Folder:* api-spec (Open API yaml file)
- *Folder:* user-flow-videos (videos for the application user flow)
- api structure (md file)
- feature list (md file)

## Technical Docs

- [Flowcharts Miro](https://miro.com/app/board/uXjVNeCv5Eg=/)
- [C4 Miro](https://lucid.app/lucidchart/178d00ec-643c-4efc-8dea-f55c3c6839b8/edit?invitationId=inv_31333d50-5996-44af-ada9-6711b9fc6e87&page=0_0#)
- [ERD Lucid](https://miro.com/app/board/uXjVNeCJp-o=/)
- [UCD Miro](https://miro.com/app/board/uXjVNQCESuY=/)

## Wireframes

There is a link to our wireframes: 

## Business Case

Read the business case document [here](https://docs.google.com/document/d/1U_peFeYgc_hJCgDn5f2zgD8DCw7oHGusvOfk_pKWAzI/edit?usp=sharing) <br>
Or download it by clicking this link: [business case](documents/Business_Case.pdf)

## Additional Files

[Initial Feature List](documents/FeatureList_UserStories.docx) <br>
[Financial Graphics](documents/Graphs.xlsx)

## Implemented Topics

### Self-Study Topics

#### Level 1
- Use of higher order functions
- Some SASS features are being used (not entirely)

#### Level 2
- None

#### Level 3
- Exceptions as first class citizens of the code
- Use of external API

### Class Taught Topics
- Graphs
- Maps

## The Team!

Meet the amazing team behind ResQ:

### Kevin Demeulenaere
![Kevin Demeulenaere](/images/team/KD.jpg)

**Role:** Quality Control & Marketing Manager

### Kevin Henri
![Kevin Henri](/images/team/KH.jpg)

**Role:** Risk & Security Manager

### Vincent Van Zele
![Vincent Van Zele](/images/team/VVZ.jpg)

**Role:** CEO & AVA Designer

### Ties Verhé
![Ties Verhé](/images/team/TV.png)

**Role:** Network & Server Manager

### Bram Lahousse
![Bram Lahousse](/images/team/BL.jpg)

**Role:** R&D Manager
