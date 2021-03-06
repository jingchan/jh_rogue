import datetime
import json

import django.http as http
import django.shortcuts as shortcuts
from django.template import RequestContext
from django.core.urlresolvers import reverse
from django.contrib.contenttypes.models import ContentType
from django.http import Http404

def generate_map(request, map_width, map_height):
    from map.models import Map
    from mob.models import Mob
    
    width = int(map_width)
    height = int(map_height)
    
    tiles, starting_locations = Map.objects.generate_random_map(width, height)
    mobs = Mob.objects.generate_random_mobs(20, width, height)
    
    return shortcuts.render_to_response('core/core.html',
                                        {'response': "%s-----%s-----%s" % (json.dumps(tiles), 
                                                                           json.dumps(mobs), 
                                                                           json.dumps(starting_locations))},
                                        context_instance = RequestContext(request),
                                        mimetype = "application/json")

def generate_tile_map(request, width, height):
    from map.models import Map
    
    width = int(map_width)
    height = int(map_height)
    
    map, start_locations = json.dumps(Map.objects.generate_random_map(width, height))
    
    return shortcuts.render_to_response('core/core.html',
                                        {'response': map},
                                        context_instance = RequestContext(request),
                                        mimetype = "application/json")