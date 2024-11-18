Feature: PlayList Feature

  @api
  Scenario: Create new playlist
    Given user pass the payload
    When user hit the endpoint
    Then user should get the response