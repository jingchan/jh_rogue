import json
import random

from django.db import models
from django.contrib.contenttypes.models import ContentType
from django.contrib.contenttypes import generic
from django.core.exceptions import ObjectDoesNotExist
from django.contrib.auth.models import User

from django_extensions.db.fields import CreationDateTimeField, ModificationDateTimeField

class MobType(models.Model):
    name = models.TextField()
    description = models.TextField()
    
    def __unicode__(self):
        return self.name
    
class MobManager(models.Manager):
    def generate_random_mobs(self, number, width, height):
        from core.utils import get_random_vector
        mobs = []
        for i in range(number):
            position_vector = get_random_vector(0, width, 0, height, 0.0, 0.0)
            direction_vector = get_random_vector(-1.0, 1.0, -1.0, 1.0, -1.0, 1.0)
            mob = self.get_random_mob()
            asset_1 = 'http://www.google.com/images/logos/ps_logo2.png'
            mobs.append([mob.id, position_vector, direction_vector, asset_1])
        return mobs
    
    def get_random_mob(self):
        mob_count = self.model.objects.all().count()
        mob_id = random.randint(1, mob_count)
        return self.model.objects.get(id = mob_id)
    
class Mob(models.Model):
    name = models.TextField()
    description = models.TextField()
    type = models.ForeignKey(MobType)
    asset = models.ImageField(upload_to = 'mob')
    
    objects = MobManager()
    
    def __unicode__(self):
        return self.name