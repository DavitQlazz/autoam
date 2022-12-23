Feature: Tasks - Full Save functionality for Urgent Task

  Scenario Outline: Create new Task about Urgent Task, fill all fields, compare data and after all removing the task.
    Given the "https://zimiecms.synisys.com/zwe-dev" page
    When I log in using "sisautousertest" as the username and "sisAutoUserTest1!" as the password
    And I click on "My Workspace" button
    And I click on "All Cases" button
    And I wait until the portfolio is opened
    And I click on "Filter" icon
    And I fill "DRAFT" value in "Case Number" field of the "Filter"
    And I click on the "1st" item of the "Case Number" column in the "1st" table
    And I click on "Tasks" button
    And I click on "Create" button of the "Tasks"
    And I select the "Urgent Task" value in the "Select Type" select
    And I click on "Create" button of the "Create New Task"

    And I fill the following fields to these values:
      | Description  | Description value  |          |
      | Task Details | Task Details value | textarea |

    And I pick the following date to these fields:
      | Requested Start Date | <date> |
      | Requested End Date   | <date> |
      | Actual Start Date    | <date> |
      | Date Completed       | <date> |

    And I select the following values for these fields:
      | Task Responsibles    | Sveta Admin |
      | Created on behalf of | Sveta Admin |

    # Task Documents
    And I click on "Add" button of the "Task Documents"
    And I fill the following fields to these values:
      | Title       | Doc title        |          |
      | Description | Description text | textarea |

    And I upload the following files accordingly:
      | Document    | 1.png |
      | Signed Copy | 2.png |
    And I select the "Affidavit of Service by a Litigant in Person" value in the "Document Type" select
    And I click on "Add" button of the "Task Documents"

    #Task Signed Copy
    And I click on "Add" button of the "Task Signed Copy"
    And I fill the following fields to these values:
      | Title       | Signed Doc title        |          |
      | Description | Signed Description text | textarea |

    And I upload the following files accordingly:
      | Document    | 3.png |
      | Signed Copy | 4.png |

    And I click on "Add" button of the "Task Signed Copy"

    And I click on "Save & Close" button of the "Task"
    And I wait 5 seconds
    And I click on "My Workspace" button
    And I click on "Tasks Created by Me" button
    And I click on the "1st" item of the "Task Number" column in the "1st" table
    Then the following values should be displayed accordingly:
      | Task Type            | Urgent Task        |
      | Task Sub-Type        | NO DATA            |
      | Task Details         | Task Details value |
      | Requested Start Date | <date>             |
      | Requested End Date   | <date>             |
      | Actual Start Date    | <date>             |
      | Date Completed       | <date>             |
      | Task Responsibles    | Sveta/Admin        |
      | Created on behalf of | Sveta Admin        |

    Then the table should be the following:
      | Doc title | Description text |

    And I click on "Actions" button
    And I click on "Submit" button

#    Workflow Actions
    And the "State: Pending" message should be displayed
    And I click on "Actions" button
    And I click on "Complete" button
    And the "State: Completed" text should be displayed
    And I click on "More" icon
    And I click on "Delete" button
    And I click on "Yes" button
    And the "You have successfully deleted this item." text should be displayed

    Examples:
      | date        |
      | 3 JUL 2020  |
#      | 15 JUL 2020 |
#      | 29 JUL 2020 |
