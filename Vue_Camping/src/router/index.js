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
    path: '/sns',
    component: () => import('../components/SnS/SnsMain.vue'),
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
    name: 'SnsMyLikeFeed',
    path: '/sns/myLikeFeed/:email',
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
    path: '/used/myUsed',
    component: () => import('@/components/UsedPractice/UsedMyPage.vue'),
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
    name: 'ChatRoom',
    path: '/chat/rooms',
    component: () => import('@/components/UsedPractice/room.vue'),
  },
  {
    name: 'ChatRoom2',
    path: '/chat/rooms2',
    component: () => import('@/components/UsedPractice/Chatting.vue'),
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
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router