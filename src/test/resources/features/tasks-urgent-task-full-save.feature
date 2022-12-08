Feature: Tasks - Full Save functionality for Urgent Task

  Scenario: This scenario creates case by api call, create new Task about Urgent Task,
  fill all fields, compare data and after all remove case.
    When I open the application
    And I login as "Automation user"
    And I click on "My Workspace" button
    And I click on "All Cases" button
    And I wait until the portfolio is opened
    And I click on "Filter" icon
    And I fill "DRAFT" value in "Case Number" field of the "Filter"
    And I click on the "1st" item of the "Case Number" column in the "1st" table
    And I click on "Tasks" button
    And I click on "Create" button of the "Tasks"
    And I select the "Urgent Task" value in "Select Type"
    And I click on "Create" button of the "Create New Task"

    And And I fill the following fields to these values:
      | Description  | Description value  |          |
      | Task Details | Task Details value | textarea |

    And And I pick the following date to these fields:
      | Requested Start Date | 3/JUL/2020 |
      | Requested End Date   | 4/JUL/2020 |
      | Actual Start Date    | 3/JUL/2020 |
      | Date Completed       | 4/JUL/2020 |

    And I select the following values for these fields:
      | Task Responsibles    | Honourable Mrs Justice Mavangira, JA |
      | Created on behalf of | Honourable Mrs Justice Mavangira, JA |

    And I click on "Tasks/Document/Attach Document" button
    And I wait until the "Popup/Dialog/Material Dialog" popup window is opened
    And I fill the "Title Test" value in "Document/Document/Title" textbox
    And I upload the "seleniumtest.png" file in "Document/Document/Document" form
    And I upload the "seleniumtest.png" file in "Document/Document/Signed Copy" form
    And I fill the "Description Test" value in "Document/Document/Description" textarea
#    And I select the "English" value in "Document/Document/Language" searchable combo
    And I select the "Affidavit of Service by a Litigant in Person" value in "Document/Document/Document Type"
    And I click on "Tasks/Document/Add Document" button
    And I wait until the "Popup/Dialog/Material Dialog" popup window is closed

    And I click on "Tasks/Document/Attach Signed Copy" button
    And I wait until the "Popup/Dialog/Material Dialog" popup window is opened
    And I fill the "Title Test" value in "Document/Document/Title" textbox
    And I upload the "seleniumtest.png" file in "Document/Document/Document" form
    And I upload the "seleniumtest.png" file in "Document/Document/Signed Copy" form
    And I fill the "Description Test" value in "Document/Document/Signed Copy Description" textarea
#    And I select the "English" value in "Document/Document/Language" searchable combo
    And I click on "Tasks/Document/Add Signed Copy" button
    And I wait until the "Popup/Dialog/Material Dialog" popup window is closed

    And I scroll to header and click on "Header/Buttons/Save and Close" button
    And I wait until the view mode is opened
    And I click on "Navigations/Navigation/Navigation" button
    And I click on "Navigations/Navigation/Tasks" button
    And I click on the "Task Number" column of "Urgent Task" name of "Task Type" column in "Portfolio/Portfolio/Table" table
    And I click on "Header/Buttons/Edit" button

#    Compare
    And The "Description Test" value should be appear in "Tasks/General/Description" textbox
    And The "03 Jul 2020" date should be selected as "Tasks/General/Requested Start Date"
    And The "04 Jul 2020" date should be selected as "Tasks/General/Requested End Date"
    And The "03 Jul 2020" date should be selected as "Tasks/General/Actual Start Date"
    And The "04 Jul 2020" date should be selected as "Tasks/General/Date Completed"
    And The following values should be selected in "Tasks/General/Task Responsibles" multi select combo
      | Simona Green |
    And The following value should be appear in "Tasks/General/Task Details" textarea
    """
    Task Details Test
    """
    And The "Tasks/Document/Table" table should be the following
      | Title Test | Description Test |  |
    And The "Simona Green" value should be selected in "Tasks/General/Created on Behalf of" searchable combo

#    Workflow Actions
    And I scroll to header and click on "Header/Buttons/Save and Close" button
    And I wait until the view mode is opened
    And I click on "Workflow/Actions/Actions" button
    And I click on "Tasks/Actions/Submit" button
    And I wait until the "Workflow/Actions/Previous State" element is visible
    And The "Pending" data should be "Workflow/Actions/Current State Name" read only
    And I click on "Workflow/Actions/Actions" button
    And I click on "Tasks/Actions/Complete" button
    And I wait until the "Workflow/Actions/Future State" element is invisible
    And I wait until the action map is opened
    And The "Completed" data should be "Workflow/Actions/Current State Name" read only

  Scenario Outline: asd
    When I click on "<test>" button
    When I click on "<field>" button
    Examples:
      | test | field |
      | asd  | sef   |
      | sdf  | sdf   |
      | df   | sdf   |
      | asd  |       |
      | asd  |       |
      | asd  |       |
