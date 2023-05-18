Feature:ASPNET Awesome Demo Page

  Scenario: Search user ID
    Given that the user access the demo awesome webpage
    And the user navigate to Grid with Filter Row section
    When the user set the following filters
      | columnName | value        |
      | Person     | Miguel       |
      | Food       | French toast |
    Then the system will return all the values
      | id  | person | food         | country       | date      | meals  | chef         |
      | 639 | Miguel | French toast | Stone Cutter  | 4/21/2013 | Rice   | Josh Baskin |

