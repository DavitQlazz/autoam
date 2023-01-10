Feature: Tasks - Full Save functionality for Affidavits

  Scenario Outline: Create new Task about Affidavits Task, fill all fields, compare data and after all removing the Affidavits.
    Given the "https://zimiecms.synisys.com/zwe-dev" page
    When I log in using "sisautousertest" as the username and "sisAutoUserTest1!" as the password
    And I click on "My Workspace" button
    And I click on "All Cases" button
    And I wait until the portfolio is opened
    And I click on "Filter" icon
    And I fill "DRAFT" value in "Case Number" field of the "Filter"
    And I select the "Anna Vardanyan Public" value in the "Party" select
    And I click on the "1st" item of the "Case Number" column in the "1st" table
    And I click on "Affidavits" button
    And I click on "Create" text of the "Affidavits"
    And I select the "Affidavit of Service by a Litigant in Person" value in the "Select Type" select
    And I click on "Create" button of the "Create New Affidavit"

    And I select the following values for these fields:
      | Party                 | Anna Vardanyan Public / Applicant    |
      | Related Document Type | Summon Commencing Action             |
      | Created on behalf of  | Honourable Mrs Justice Mavangira, JA |
      | Delivery Method       | Personal delivery                    |

    And I pick the following date to these fields:
      | Affidavit Date            | <date> |
      | Date/Time Document Served | <date> |
      | Sworn Date                | <date> |

    And I fill the following fields to these values:
      | Location                   | Location A                   |
      | Commissioner of Oaths      | Commissioner of Oaths B      |
      | Persons Document Served On | Persons Document Served On C |

    # Task Documents
    And I click on "Add" button of the "Affidavit Documents"
    And I fill the following fields to these values:
      | Title       | Doc title        |          |
      | Description | Description text | textarea |

    And I upload the following files accordingly:
      | Document    | 1.png |
      | Signed Copy | 2.png |

    And I select the "Affidavit of Service by a Litigant in Person" value in the "Document Type" select
    And I click on "Add" button of the "Affidavit Documents"

    #Task Signed Copy
    And I click on "Add" button of the "Affidavit Signed Copy"
    And I fill the following fields to these values:
      | Title       | Signed Doc title        |          |
      | Description | Signed Description text | textarea |

    And I upload the following files accordingly:
      | Document    | 3.png |
      | Signed Copy | 4.png |

    And I click on "Add" button of the "Affidavit Signed Copy"

    And I click on "Save & Close" button of the "Affidavit"

    And I wait 5 seconds

    Then the following values should be displayed accordingly:
      | Title                      | Anna Vardanyan Public                |
      | Party                      | Anna Vardanyan Public                |
      | Related Document Type      | Summon Commencing Action             |
      | Created on behalf of       | Honourable Mrs Justice Mavangira, JA |
      | Delivery Method            | Personal delivery                    |
      | Location                   | Location A                           |
      | Commissioner of Oaths      | Commissioner of Oaths B              |
      | Persons Document Served On | Persons Document Served On C         |
      | Sworn Date                 | <date>                               |
#      | Date/Time Document Served  | <date>                               | can't check
      | Affidavit Date             | <date>                               |

#    Then the table should be the following:
#      | Doc title | Description text |

    Then the attached file of "Affidavit Signed Copy" should contains the "Signed Doc title" file title

    And I click on "Actions" button
    And I click on "Action test1" button

#    Workflow Actions
    And the "State: Inch State es crtin" text should be displayed
    And I click on "Actions" button
    And I click on "Submit" button
    And the "State: Pending Compliance Check (Z)" text should be displayed
    And I click on "Actions" button
    And I click on "Compliance Checked" button
    And the "State: Approved" text should be displayed
    And I click on "More" icon
    And I click on "Delete" button
    And I click on "Yes" button
    And the "You have successfully deleted this item." text should be displayed

    Examples:
      | date       |
      | 3 JUL 2020 |
#      | 15 JUL 2020 |
