import os

try:
    from __revision__ import __revision__
except:
    __revision__ = 'develop'

metadata = {
    'name': "rpgserver",
    'version': "1.0",
    'release': __revision__,
    'url': 'http://www.rpgserver.org',
    'author': 'genghisu',
    'author_email': 'genghisu@gmail.com',
    'admin': 'genghisu@gmail.com',
    'dependencies': (
        'simplejson',
        'django-haystack',
        'django-debug-toolbar',
        'South',
        'django-extensions',
        'html5lib',
        'pysolr',
        'django-nose',
        'django-compress',
    ),
    'description': 'Server for web based game',
    'license': 'Private',
}
