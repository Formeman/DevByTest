$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("devByTest.feature");
formatter.feature({
  "line": 1,
  "name": "Dev.by features",
  "description": "In this feature file we are going to store dev.by feature",
  "id": "dev.by-features",
  "keyword": "Feature"
});
formatter.before({
  "duration": 12303215948,
  "status": "passed"
});
formatter.background({
  "line": 4,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 5,
  "name": "I open main page",
  "keyword": "Given "
});
formatter.match({
  "arguments": [
    {
      "val": "open",
      "offset": 2
    }
  ],
  "location": "MyStepdefs.iOpenMainPage(String)"
});
formatter.result({
  "duration": 5119619180,
  "status": "passed"
});
formatter.scenario({
  "line": 8,
  "name": "Login with invalid credentials (imperative)",
  "description": "",
  "id": "dev.by-features;login-with-invalid-credentials-(imperative)",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 7,
      "name": "@login"
    }
  ]
});
formatter.step({
  "line": 9,
  "name": "I click on login icon",
  "keyword": "When "
});
formatter.step({
  "line": 10,
  "name": "I enter \"someLogin\" as login",
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "I enter \"somePassword\" password",
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "I click on login button",
  "keyword": "And "
});
formatter.step({
  "line": 13,
  "name": "I should see error message",
  "keyword": "Then "
});
formatter.match({
  "location": "MyStepdefs.iClickOnLoginIcon()"
});
formatter.result({
  "duration": 1579278047,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "someLogin",
      "offset": 9
    }
  ],
  "location": "MyStepdefs.iEnterAsLogin(String)"
});
formatter.result({
  "duration": 1684200423,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "somePassword",
      "offset": 9
    }
  ],
  "location": "MyStepdefs.iEnterPassword(String)"
});
formatter.result({
  "duration": 767363836,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.iClickOnLoginButton()"
});
formatter.result({
  "duration": 776660053,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.iShouldSeeErrorMessage()"
});
formatter.result({
  "duration": 320354743,
  "status": "passed"
});
formatter.after({
  "duration": 723559666,
  "status": "passed"
});
formatter.before({
  "duration": 4802793997,
  "status": "passed"
});
formatter.background({
  "line": 4,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 5,
  "name": "I open main page",
  "keyword": "Given "
});
formatter.match({
  "arguments": [
    {
      "val": "open",
      "offset": 2
    }
  ],
  "location": "MyStepdefs.iOpenMainPage(String)"
});
formatter.result({
  "duration": 3501399659,
  "status": "passed"
});
formatter.scenario({
  "line": 16,
  "name": "Login with invalid credentials (declarative)",
  "description": "",
  "id": "dev.by-features;login-with-invalid-credentials-(declarative)",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 15,
      "name": "@login"
    },
    {
      "line": 15,
      "name": "@regression"
    }
  ]
});
formatter.step({
  "line": 17,
  "name": "I go to login page",
  "keyword": "When "
});
formatter.step({
  "line": 18,
  "name": "I login with \"login\" as login and \"password\" as password",
  "keyword": "And "
});
formatter.step({
  "line": 19,
  "name": "I should see error message",
  "keyword": "Then "
});
formatter.match({
  "location": "MyStepdefs.iGoToLoginPage()"
});
formatter.result({
  "duration": 1200424611,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "login",
      "offset": 14
    },
    {
      "val": "password",
      "offset": 35
    }
  ],
  "location": "MyStepdefs.iLoginWithAsLoginAndAsPassword(String,String)"
});
formatter.result({
  "duration": 2216667047,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.iShouldSeeErrorMessage()"
});
formatter.result({
  "duration": 139898276,
  "status": "passed"
});
formatter.after({
  "duration": 660213104,
  "status": "passed"
});
});