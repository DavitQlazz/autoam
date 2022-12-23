Feature: Party-Creating Individual Party

  Scenario Outline:Create new Party, fill all fields, compare data and after removing the task.
    Given the "https://k8s-dev7.synisys.com/ecase-court" page
    When I log in using "TestTestikyan" as the username and "TestTestikyan1" as the password
    And I click on "Parties" button
    And I click on "Create" button
    And I select the "Individual" value in the "Select one" select
    And I click on "Select" button
    And I wait 2 seconds

    And I upload to "Pictures" the following 7 files:
      | 1.png |
      | 2.png |
      | 3.png |
      | 4.png |
      | 5.png |
      | 6.png |
      | 7.png |

    And I fill the following fields to these values:
      | First Name                     | First Name Test               |          |
      | Last Name                      | Last Name Test                |          |
      | Given Name                     | Given Name Test               |          |
      | Other Name                     | Other Name Test               |          |
      | National Identification Number | 123456789                     |          |
      | Passport No                    | 987654321                     |          |
      | Driver License No              | 111111111111                  |          |
      | Initials                       | Initials value                |          |
      | URL                            | https://translate.google.com/ |          |
      | Description                    | Description value             | textarea |
      | Primary Email                  | <primary email>               |          |
      | Secondary Email                | <secondary email>             |          |
      | Mobile Phone Number            | <phone>                       |          |
      | Home Phone Number              | <phone>                       |          |
      | Other Phone Number             | <phone>                       |          |
      | Business Phone Number          | <phone>                       |          |

    And I pick the following date to these fields:
      | Date of Birth | 3 JUL 2017 |

    And I select the following values for these fields:
      | Gender                         | <gender>      |
      | Citizenship                    | <citizenship> |
      | Marital Status                 | Single        |
      | Language                       | English       |
      | Educational Level              | Master        |
      | Disability                     | Deaf          |
      | Disability                     | Physical      |
      | Preferred Communication Method | Email         |
      | Mobile Phone Number Code       | <code>        |
      | Home Phone Number Code         | <code>        |
      | Business Phone Number Code     | <code>        |
      | Other Phone Number Code        | <code>        |


    And I click on "Save & Close" button of the "Party"
    And I wait 5 seconds
    Then the following values should be displayed accordingly:
      | First Name                     | First Name Test               |
      | Last Name                      | Last Name Test                |
      | Given Name                     | Given Name Test               |
      | Other Name                     | Other Name Test               |
      | National Identification Number | 123456789                     |
      | Passport No                    | 987654321                     |
      | Driver License No              | 111111111111                  |
      | Initials                       | Initials value                |
      | URL                            | https://translate.google.com/ |
      | Description                    | Description value             |
      | Primary Email                  | <primary email>               |
      | Secondary Email                | <secondary email>             |
      | Mobile Phone Number            | <phone>                       |
      | Home Phone Number              | <phone>                       |
      | Other Phone Number             | <phone>                       |
      | Business Phone Number          | <phone>                       |
      | Date of Birth                  | 03.07.2017                    |
      | Gender                         | <gender>                      |
      | Citizenship                    | <citizenship>                 |
      | Marital Status                 | Single                        |
      | Language                       | English                       |
      | Educational Level              | Master                        |
      | Disability                     | Deaf                          |
      | Disability                     | Physical                      |
      | Preferred Communication Method | Email                         |
      | Mobile Phone Number Code       | <code>                        |
      | Home Phone Number Code         | <code>                        |
      | Business Phone Number Code     | <code>                        |
      | Other Phone Number Code        | <code>                        |


    Examples:
      | phone     | code           | gender | citizenship | primary email           | secondary email           |
      | 123456789 | Armenia (+374) | Male   | Uganda      | primary@synisystest.com | secondary@synisystest.com |
