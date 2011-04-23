from django.db import models
from django.contrib.contenttypes.models import ContentType
from django.contrib.contenttypes import generic
from django.core.exceptions import ObjectDoesNotExist
from django.contrib.auth.models import User

from django_extensions.db.fields import CreationDateTimeField, ModificationDateTimeField
from item.models import Item

class Archtype(models.Model):
    name = models.TextField()
    description = models.TextField()
    
class Character(models.Model):
    name = models.TextField()
    archtype = models.ForeignKey(Archtype)
    user = models.ForeignKey(User)
    
class Inventory(models.Model):
    character = models.OneToOneField(Character)

class InventoryItem(models.Model):
    inventory = models.ForeignKey(Inventory)
    item = models.ForeignKey(Item)
    
class Stash(models.Model):
    character = models.OneToOneField(Character)
    
class StashItem(models.Model):
    stash = models.ForeignKey(Stash)
    item = models.ForeignKey(Item)