import datetime

import django.http as http
import django.shortcuts as shortcuts
from django.template import RequestContext
from django.core.urlresolvers import reverse
from django.contrib.contenttypes.models import ContentType
from django.http import Http404

def generate_map(request):
    from map.models import Map
    
    response = Map.objects.generate_random_map(100, 100)
    
    return shortcuts.render_to_response('core/core.html',
                                        {'response':response},
                                        context_instance = RequestContext(request),
                                        mimetype = "application/json")