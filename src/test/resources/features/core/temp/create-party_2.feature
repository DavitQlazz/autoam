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


    # Employment

    And I click on "Add" button of the "Employment" table
    And I am waiting until the "Employment" pop-up window appears

    And I fill the following fields to these values:
      | Employer Name | Employer Name text |          |
      | Position Held | Position Held text |          |
      | Details       | Details text       | textarea |

    And I pick the following date to these fields:
      | Employment Start Date | 11 NOV 2022 |
      | Employment End Date   | 29 DEC 2022 |

    And I click on "Add" button of the "Employment"


# Relative Information

    And I click on "Add" button of the "Relative Information" table
    And I fill the following fields to these values:
      | Full Name                      | Full Name text |
      | National Identification Number | 123456789      |
      | Relation Type                  | Relation text  |

    And I pick the following date to these fields:
      | Date of Birth | 01 DEC 2022 |

    And I select the following values for these fields:
      | Gender | <gender> |

    And I click on "Add" button of the "Relative Information"


# "Party Addresses"

    And I click on "Add" button of the "Party Addresses" table
    And I am waiting until the "Address" pop-up window appears

    And I select the following values for these fields:
      | Address Type | Place of Birth      |
#      | Country      | Zimbabwe            |Auto-select
      | Province     | Harare              |
      | City/Town    | Harare              |
      | District     | Harare              |
      | Ward         | Unincorporated Area |

    And I fill the following fields to these values:
      | Street | Street 1 |

    And I click on "Add" button of the "Address"
    And I check the "Address of Service" radio button of the "Party Addresses" table

    # "Related Users"

    And I click on "Add" button of the "Related Users" table

    And I fill the following fields of the "Search and Add Related Users" popup
      | First Name     | Ann  |
      | Last Name      | Jane |
      | E-Mail Address | a    |
      | Phone Number   | 1    |

    And I click on "Search" button of the "Search and Add Related Users"
    And I check the "Ann Jane" radio button of the "Search and Add Related Users" table
    And I click on "Add" button of the "Search and Add Related Users"


# "Related Cases"
# will write this section after bug fix

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

    Then the "Employment" table should be the following:
      | Employer Name text | Position Held text | 11.11.2022 | 29.12.2022 |

    Then the "Relative Information" table should be the following:
      | Full Name text | 123456789 | <gender> | Relation text |

    Then the "Party Addresses" table should be the following:
      | Place of Birth | Zimbabwe, Harare, Harare, Harare, Unincorporated Area, Street 1 |

    Then the "Related Users" table should be the following:
      | Ann Jane | 05.10.2021 04:00 | ann.mang@synisys.com | 123456789 |


    Examples:
      | phone     | code           | gender | citizenship | primary email           | secondary email           |
      | 123456789 | Armenia (+374) | Male   | Uganda      | primary@synisystest.com | secondary@synisystest.com |
