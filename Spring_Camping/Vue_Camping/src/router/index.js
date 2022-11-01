import {
  createRouter,
  createWebHistory
} from "vue-router"

const routes = [{
    name: 'Home',
    path: '/',
    component: () => import('../views/Home.vue'),
  },
  {
    name: 'SnsMain',
    path: '/sns/:hashtag',
    component: () => import('../components/SnS/SnsMain.vue'),
    props :true
  },
  {
    name: 'SnsDetail',
    path: '/sns/detail/:writeNo',
    component: () => import('../components/SnS/SnsDetailFeed.vue'),
  },
  {
    name: 'SnsWrite',
    path: '/sns/write',
    component: () => import('../components/SnS/SnsWrite.vue'),
  },
  {
    name: 'SnsRewrite',
    path: '/sns/rewrite/:writeNo',
    component: () => import('../components/SnS/SnsRewrite.vue'),
  },
  {
    name: 'SnsMyFeed',
    path: '/sns/myFeed/:nickname',
    component: () => import('../components/SnS/SnsMyFeed.vue'),
  },
  {
    name: 'SnsMyTagFeed',
    path: '/sns/myTagFeed/:nickname',
    component: () => import('../components/SnS/SnsMyTagFeed.vue'),
  },
  {
    name: 'SnsMyLikeFeed',
    path: '/sns/myLikeFeed/:nickname',
    component: () => import('../components/SnS/SnsMyLikeFeed.vue'),
  },
  {
    path: '/recru/detail/:recruId',
    name: 'recruDetail',
    component: () => import('@/components/recruit/RecruDetail.vue'),
    props: true
  },
  {
    name: 'usedInsert',
    path: '/used/usedInsert',
    component: () => import('@/components/UsedPractice/UsedInsert.vue'),
  },
  {
    name: 'usedDetail',
    path: '/used/usedDetail/:usedId',
    component: () => import('@/components/UsedPractice/UsedDetail.vue'),
    props: true
  },
  {
    name: 'usedUpdate',
    path: '/used/usedUpdate/:usedId',
    component: () => import('@/components/UsedPractice/UsedUpdate.vue'),
    props: true
  },
  {
    name: 'LoginSignup',
    path: '/Login',
    component: () => import('@/components/User/LoginSignup.vue'),
  },
  {
    name: 'SocialLogin',
    path: '/SocialLogin',
    component: () => import('@/components/User/LoginSignup.vue'),
  },
  {
    name: 'UserInfo',
    path: '/MyInfo',
    component: () => import('@/components/User/UserInfo.vue'),
  },
  {
    name: 'CampList',
    path: '/CampList',
    component: () => import('@/components/Camping/CampList.vue'),
  },
  {
    name: 'CampDetail',
    path: '/Camp/:campId',
    component: () => import('@/components/Camping/CampDetail.vue'),
  },
  {
    name: 'CampRegister',
    path: '/CampRegister',
    component: () => import('@/components/Camping/CampRegister.vue'),
  },
  {
    name: 'CampModify',
    path: '/CampModify/:campId',
    component: () => import('@/components/Camping/CampModify.vue'),
  },
  {
    name: 'RecruList',
    path: '/RecruList',
    component: () => import('@/components/recruit/RecruList.vue'),
  },
  {
    name: 'RecruInsert',
    path: '/recru/RecruInsert',
    component: () => import('@/components/recruit/RecruInsert.vue'),
  },
  {
    name: 'RecruInsertByCamp',
    path: '/recru/RecruInsert/:campName/:campAddress',
    component: () => import('@/components/recruit/RecruInsert.vue'),
    props : true
  },
  {
    name: 'RecruMypage',
    path: '/recru/RecruMypage',
    component: () => import('@/components/recruit/RecruMypageView.vue'),
  },
  {
    name: 'DepositMypage',
    path: '/recru/DepositMypage',
    component: () => import('@/components/recruit/DepositMypageView.vue'),
  },
  {
    name: 'EntryMypage',
    path: '/recru/EntryMypage',
    component: () => import('@/components/recruit/EntryMypageView.vue'),
  },
  {
    name: 'Usage',
    path: '/Usage',
    component: () => import('@/components/note/Usage.vue'),
  },
  {
    name: 'WriteNote',
    path: '/WriteNote',
    component: () => import('@/components/note/WriteNote.vue'),
  },
  {
    name: 'MyNoteInfo',
    path: '/MyNoteInfo/:noteId',
    component: () => import('@/components/note/MyNoteInfo.vue'),

  },
  {
    name: 'ReadOnlyNote',
    path: '/ReadOnlyNote/:noteId',
    component: () => import('@/components/note/ReadOnlyNote.vue'),

  },
  {
    name: 'MynoteList',
    path: '/MynoteList',
    component: () => import('@/components/note/MynoteList.vue'),
  },
  {
    name: 'InvitedList',
    path: '/InvitedList',
    component: () => import('@/components/note/InvitedList.vue'),
  },
  {
    name: 'usedMain',
    path: '/used/usedMain',
    component: () => import('@/components/UsedPractice/UsedMain.vue'),
  },
  {
    name: 'usedReview',
    path: '/used/usedReview',
    component: () => import('@/components/UsedPractice/UsedReview.vue'),
  },
  {
    name: 'ManageUser',
    path: '/Manage/User',
    component: () => import('@/components/Admin/UserManage.vue'),
  },
  {
    name: 'ManageReport',
    path: '/Manage/Report',
    component: () => import('@/components/Admin/ReportManage.vue'),
  },
  {
    name: 'myUsed',
    path: '/used/myUsed/:email',
    component: () => import('@/components/UsedPractice/UsedMyPage.vue'),
    props: true
  },
  {
    name: 'StoredCampList',
    path: '/StoredCampList',
    component: () => import('@/components/Camping/StoredCampList.vue'),
  },
  {
    name: 'UsedChat',
    path: '/usedChat',
    component: () => import('@/components/Chat/ChatView.vue'),
  },
  {
    name: 'RecruUpdate',
    path: '/recru/RecruUpdate/:recruId',
    component: () => import('@/components/recruit/RecruUpdateView.vue'),
  }, 
  {
    name: 'ReportUser',
    path: '/User/Report',
    component: () => import('@/components/User/ReportManage.vue'),
  },
  {
    name: 'RecruReview',
    path: '/recru/review/:recruId',
    component: () => import('@/components/recruit/RecruReviewView.vue'),
    props: true
  },
  {
    name: 'UsedSearch',
    path: '/used/usedSearch',
    component: () => import('@/components/UsedPractice/UsedSearch.vue'),
  },
  {
    name: 'myUsedSave',
    path: '/used/mySave',
    component: () => import('@/components/UsedPractice/UsedMySave.vue'),
    props: true
  },
  {
    name: 'SendMail',
    path: '/mail/sendMail',
    component: () => import('@/components/Mail/SendMail.vue'),
    props: true
  },
  {
    name: 'MailList',
    path: '/mail/mailList',
    component: () => import('@/components/Mail/MailList.vue'),
    props: true
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router