from django.db import models
from django.contrib.contenttypes.models import ContentType
from django.contrib.contenttypes import generic
from django.core.exceptions import ObjectDoesNotExist
from django.contrib.auth.models import User

from django_extensions.db.fields import CreationDateTimeField, ModificationDateTimeField

class ItemType(models.Model):
    name = models.TextField()
    description = models.TextField()
    ground_asset = models.ImageField(upload_to = 'items')
    inventory_asset = models.ImageField(upload_to = 'items')
    
class Item(models.Model):
    name = models.TextField()
    description = models.TextField()
    ground_asset = models.ImageField(upload_to = 'items')
    inventory_asset = models.ImageField(upload_to = 'items')
    level = models.IntegerField(default = 1)
    type = models.ForeignKey(ItemType)