from settings import *

LOCAL_DEV_SERVER = True

DATABASE_ENGINE = 'sqlite3'           # 'postgresql_psycopg2', 'postgresql', 'mysql', 'sqlite3' or 'oracle'.
DATABASE_NAME = 'django360'             # Or path to database file if using sqlite3.
DATABASE_USER = ''             # Not used with sqlite3.
DATABASE_PASSWORD = ''         # Not used with sqlite3.
DATABASE_HOST = ''             # Set to empty string for localhost. Not used with sqlite3.
DATABASE_PORT = ''             # Set to empty string for default. Not used with sqlite3.

TEST = True
ANONYMOUS_USERS_ENABLED = False

TEST_APPS = (
    'tutorials',
    'projects',
    'applications',
    'code',
    'info',
    'search',
    'django_qa',
    'django_community',
    'django_defaultcontext',
    'django_comments',
    'django_moderation',
    'django_relatedcontent',
    'django_metatagging',
    'django_utils',
    'django_multivoting',
    'django_gravatar',
    'django_openidconsumer',
    'django_reputation',
    'django_tracking',
    'django_contenthistory',
    'django_badges',
    'django_userhistory',
    'django_community_wiki',
    'tagging',
    'django_wizard',
    'core',
)