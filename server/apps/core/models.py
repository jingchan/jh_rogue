from django.db import models
from django.contrib.contenttypes.models import ContentType
from django.contrib.contenttypes import generic
from django.core.exceptions import ObjectDoesNotExist
from django.contrib.auth.models import User

from django_extensions.db.fields import CreationDateTimeField, ModificationDateTimeField
from character.models import Character
from map.models import Map
from mob.models import Mob
from item.models import Item

class GameInstanceManager(models.Manager):
    def create_game_instance(self, mode, max_players):
        instance = self.model(mode, max_players)
        instance.save()
        return instance
    
class GameInstance(models.Model):
    mode = models.TextField()
    max_players = models.IntegerField()
    
    objects = GameInstanceManager()
    
class MapInstance(models.Model):
    map = models.ForeignKey(Map)

class ConnectedCharacter(models.Model):
    instance = models.ForeignKey(GameInstance)
    map = models.ForeignKey(MapInstance)
    location = models.TextField()
    status = models.TextField()
    character = models.ForeignKey(Character)

class MobInstance(models.Model):
    map = models.ForeignKey(MapInstance)
    location = models.TextField()
    status = models.TextField()
    mob = models.ForeignKey(Mob)

class ItemInstance(models.Model):
    instance = models.ForeignKey(MapInstance)
    location = models.TextField()
    item = models.ForeignKey(Item)
    

    