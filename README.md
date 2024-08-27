# HeProMo
Productivity models for wood harvesting operations.

A description of this project can be found here:

https://journals.plos.org/plosone/article?id=10.1371/journal.pone.0244289

https://www.waldwissen.net/technik/holzernte/kalkulation/wsl_hepromo/index_EN

This repository contains the source code of HeProMo as an Eclipse project.

This project uses AssertJ, Apache FOP, and TestNG. These libraries are all licensed under the Apache 2.0 License.


# Changelog
## v.2.5.0 (August 2021)
- Calculations can be exported to CSV-files
- Separate input of risk, administration, and benefit possible
- Skidder: warning if lateral hauling distance >40m
- Data sheet: fixed incorrect presentation of thousands separator occurring with newer Java versions
- Data sheet: increased size of field "place of work"
- Optimized appearance of TitledBorder on Windows and Java >= 9
- Bugfix tower yarder / field "skyline height"
- Changed contact person
- Forwarder (round wood and energy wood): field "Show results for" in bold font
- Forwarder (round wood and energy wood): down to a screen resolution of 1280x720, all input fields remain visible
- Automatically create file hepromo-error.log if an application error occurs.
- Aligned version number of api-jar with version number of main application
