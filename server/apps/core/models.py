from django.db import models
from django.contrib.contenttypes.models import ContentType
from django.contrib.contenttypes import generic
from django.core.exceptions import ObjectDoesNotExist

from django_extensions.db.fields import CreationDateTimeField, ModificationDateTimeField

class City(models.Model):
    name = models.TextField()
    
class State(models.Model):
    name = models.TextField()
    
class Location(models.Model):
    street = models.TextField()
    city = models.ForeignKey(City)
    state = models.ForeignKey(State)
    zipcode = models.CharField(max_length = 75, default = '') 
    
class Restaurant(models.Model):
    name = models.CharField(max_length = 75)
    location = models.ForeignKey(Location)
    delivery_range = models.IntegerField(default = 0)
    active = models.BooleanField(default = True)
    
class DeliveryArea(models.Model):
    restaurant = models.ForeignKey(Restaurant)
    zipcode = models.CharField(max_length = 75, default = '')

class Food(models.Model):
    name = models.TextField()
    description = models.TextField()

class FoodPhoto(models.Model):
    food = models.ForeignKey(Food)
    photo = models.ForeignKey(Photo)
    
class FoodItem(models.Model):
    food = models.ForeignKey(Food)
    restaurant = models.ForeignKey(Restaurant)

class Menu(models.Model):
    name = models.TextField()
    restaurant = models.ForeignKey(Restaurant)
    active = models.BooleanField(default = False)

class MenuSection(models.Model):
    menu = models.ForeignKey(Menu)

class MenuItem(models.Model):
    section = models.ForeignKey(MenuSection)
    item = models.ForeignKey(FoodItem)
    
    