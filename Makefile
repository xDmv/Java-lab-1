SHELL  := /bin/bash

#COLORS
RED    := $(shell tput -Txterm setaf 1)
GREEN  := $(shell tput -Txterm setaf 2)
WHITE  := $(shell tput -Txterm setaf 7)
YELLOW := $(shell tput -Txterm setaf 3)
RESET  := $(shell tput -Txterm sgr0)

help:
	@echo '$(WHITE)Available the following targets$(RESET):'
	@echo '$(YELLOW)init:'
	@echo '  $(GREEN)init $(RESET) 	 - initialization docker images'
	@echo '$(YELLOW)development$(RESET):'
	@echo '  $(GREEN)status $(RESET) - status docker images'
	@echo '  $(GREEN)logs $(RESET) - logs docker images'
	@echo '  $(GREEN)kill $(RESET) - delete docker images in this computer'
	@echo '  $(GREEN)restart $(RESET) - delete and start docker images'
	@echo '  $(GREEN)logs $(RESET) - logs docker images'
	@echo '  $(GREEN)conf $(RESET) - settings for docker-compose file'

init:
	docker-compose up -d
status:
	docker-compose ps && docker-compose logs --tail=100
logs:
	docker-compose logs -f
kill:
	docker-compose kill && docker-compose rm -vf
restart: kill up status
ps: 
	docker-compose ps
conf:
	docker-compose config