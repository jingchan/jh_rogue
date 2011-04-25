from django.db import models
from django.contrib.contenttypes.models import ContentType
from django.contrib.contenttypes import generic
from django.core.exceptions import ObjectDoesNotExist
from django.contrib.auth.models import User
import json

from django_extensions.db.fields import CreationDateTimeField, ModificationDateTimeField

class TileTexture(models.Model):
    name = models.TextField()
    description = models.TextField()
    asset = models.ImageField(upload_to = 'tile_textures')

class MapManager(models.Manager):
    def generate_random_map(self, width, height):
        tiles = []
        for x in range(width):
            for y in range(height):
                tiles.append((x, y, 0))
        return json.dumps(tiles)
    
class Map(models.Model):
    tiles = models.TextField()
    
    objects = MapManager()