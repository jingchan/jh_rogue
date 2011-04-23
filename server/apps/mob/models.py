from django.db import models
from django.contrib.contenttypes.models import ContentType
from django.contrib.contenttypes import generic
from django.core.exceptions import ObjectDoesNotExist
from django.contrib.auth.models import User

from django_extensions.db.fields import CreationDateTimeField, ModificationDateTimeField

class MobType(models.Model):
    name = models.TextField()
    description = models.TextField()
    
class Mob(models.Model):
    name = models.TextField()
    description = models.TextField()
    type = models.ForeignKey(MobType)