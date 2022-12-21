Feature: Tasks - Case Full Save

  Scenario Outline: Create new Case
    Given The "https://k8s-dev8.synisys.com/ecase-court/workspace" page
    When I log in using "adminuser" as the username and "adminuser1" as the password
    And I click on "Court Cases" button
    And I click on "Cases" button
    And I click on "Create" button
    And I wait 2 seconds

    And I select the following values for these fields:
      | Court Registry | Supreme Court   |
      | Case Category  | Criminal Appeal |
      | Instance Level | First Appeal    |

    And I fill the following fields to these values:
      | Cause of Action | Cause of Action <text> |          |
      | Case Summary    | Case Summary <text>    | textarea |

    And I select the following values for these fields:
      | Judge President                      | Judge President Olivia/Watson |
      | Registrar / Deputy Registrar         | Registrar Tom/Green           |
      | Assigned Assistant Registrar         | Assistant Jane/Smith          |
      | Assigned Judge                       | Judge Peter/Johnson           |
      | Senior Researcher                    | Syune/Senior Researcher       |
      | Assigned Researcher                  | Syune/Senior Researcher       |
      | Assigned Clerks/Secretary/Assistants | Assistant Jane/Smith          |

    And I click on "Add" button of the "Bench Members"
    And I select the following values for these fields:
      | Judge Name | Assistant Jane/Smith |

    And I fill the following fields to these values:
      | Discharge Reason | Discharge Reason |

    And I pick the following date to these fields:
      | Allocation Date | 3 JUL 2020  |
      | Discharge Date  | 15 JUL 2020 |

    And I click on "Add" button of the "Bench Members"

    Then The table should be the following:
      | Assistant Jane Smith | 3.07.2020 00:00 | 15.07.2020 00:00 | Discharge Reason |

    And I wait 12 seconds

    Examples:
      | text        |
      | 3 JUL 2020  |
      | 15 JUL 2020 |
      | 16 JUL 2021 |
