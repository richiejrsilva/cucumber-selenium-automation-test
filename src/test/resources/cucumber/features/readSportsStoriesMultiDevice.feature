Feature: 
  	I want the ability to view the BBC sport pages both in Desktop and Mobile views. 
    I want to read a sports news story for Football, Cricket,  Tennis & Rugby Union

Scenario: Read Sports Stories
    Given the device "Samsung Galaxy S4"
    | width | height | pixelRatio |
    |  320  |   480  |     3.5    |
    When open BBC Home Page "http://www.bbc.com"
    And navigate to "Sport"
    And choose a specific sport "Golf"
    Then a sports news story at the first headline can be read "Matsuyama becomes first Asian WGC winner"
   
Scenario: Validate Page Footer
    Given my device "Samsung Galaxy S4"
    | width | height | pixelRatio |
    |  320  |   480  |     3.5    |
    When open BBC "http://www.bbc.com" "Sport" "Golf" web page
    Then all page footer link are working and labels are according
      | News     									 | /new                   				| Home - BBC News      |
      | Sport    								     | /sport   			     			| Home - BBC Sport     |
      | Weather  									 | /weather               				| BBC Weather          |
      | Shop     									 | http://shop.bbc.com/   				| BBC Shop 			   |
      | Earth    									 | /earth                 				| BBC - Earth - Home   |
      | Travel   									 | /travel               			    | BBC - Travel - Home  |
	And page footer title is "Explore the BBC"
    And there is a footer rights reserved label "Copyright © 2016 BBC."

